package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.HashSet;
import java.util.Set;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

public class BodyAssociationManager implements
      GraphListener<BodyVertex, BodyEdge> {
   private BodyGraph graph;

   public BodyAssociationManager(BodyGraph graph) {
      this.graph = graph;
   }

   /**
    * This will create bodies for connected sets of BodyVertices which do not
    * have any Bodies attached.
    * 
    * @param initialState
    *           The world coordinates of where to spawn the bodies.
    * @param factory
    *           The RigidBodyFactory to use for spawning new bodies.
    */
   public void spawnMissingBodies(NewtonianState initialState,
                                  RigidBodyFactory factory) {
      ConnectivityInspector<BodyVertex, BodyEdge> inspector = new ConnectivityInspector<BodyVertex, BodyEdge>(
            graph);
      for (Set<BodyVertex> set : inspector.connectedSets()) {
         RigidBody assignedBody = findAssignedBody(set);
         if (assignedBody == null) {
            RigidBody newBody = factory.createDynamicBody(initialState);
            Set<RigidBody> replacedBodies = replaceBodiesInSet(newBody, set);
            assert replacedBodies.size() == 0;
         }
      }
   }

   private RigidBody findAssignedBody(Set<BodyVertex> set) {
      boolean bodyLocked = false;
      RigidBody foundBody = null;
      for (BodyVertex vertex : set) {
         if (bodyLocked) {
            assert vertex.getBody() == foundBody;
         } else {
            bodyLocked = true;
            foundBody = vertex.getBody();
         }
      }
      return foundBody;
   }

   public void vertexAdded(GraphVertexChangeEvent<BodyVertex> event) {
   }

   public void vertexRemoved(GraphVertexChangeEvent<BodyVertex> event) {
   }

   public void edgeAdded(GraphEdgeChangeEvent<BodyVertex, BodyEdge> event) {
      if (event.getType() != GraphEdgeChangeEvent.BEFORE_EDGE_ADDED) {
         return;
      }

      BodyVertex first = event.getEdgeSource();
      BodyVertex second = event.getEdgeSource();

      // Possible cases:
      // - Neither has a body attached
      // - Both have the same body attached
      // - Only one has a body attached
      // - Both have a different body attached

      if (first.getBody() == second.getBody()) {
         // Both vertices are attached to the same body (or both are null),
         // nothing needs to be done
         return;
      } else {
         // Vertices are attached to different bodies or one of both is null,
         // merge bodies and attach all nodes to new body.
         RigidBody merged = mergeBodies(first.getBody(), second.getBody());
         assert merged != null;
         Set<RigidBody> replacedBodies = new HashSet<RigidBody>();

         if (first.getBody() != merged) {
            replacedBodies.addAll(replaceBodiesOnConnected(first, merged));
         }
         if (second.getBody() != merged) {
            replacedBodies.addAll(replaceBodiesOnConnected(second, merged));
         }

         // If everything works as intended, there should only ever be a single
         // body that is being replaced.
         assert replacedBodies.size() <= 1;
         for (RigidBody deletee : replacedBodies) {
            deletee.destroy();
         }
      }
   }

   private RigidBody mergeBodies(RigidBody first, RigidBody second) {
      if (first == null) {
         return second;
      } else if (second == null) {
         return first;
      } else {
         return first;
      }
   }

   private Set<RigidBody> replaceBodiesOnConnected(BodyVertex vertex,
                                                   RigidBody newBody) {
      ConnectivityInspector<BodyVertex, BodyEdge> inspector = new ConnectivityInspector<BodyVertex, BodyEdge>(
            graph);
      Set<RigidBody> replacedBodies = replaceBodiesInSet(newBody,
                                                         inspector
                                                               .connectedSetOf(vertex));
      return replacedBodies;
   }

   private Set<RigidBody> replaceBodiesInSet(RigidBody newBody,
                                             Set<BodyVertex> connectedSet) {
      Set<RigidBody> replacedBodies = new HashSet<RigidBody>();
      for (BodyVertex connected : connectedSet) {
         RigidBody oldBody = connected.getBody();

         // Track replaced bodies
         if (oldBody != null) {
            assert connected.getBody() != newBody;
            replacedBodies.add(oldBody);
         }

         // Detach behaviors from old body and attach them to the new body
         for (Plug changedPlug : connected.getPlugs()) {
            if (oldBody != null) {
               changedPlug.notifyDetach(oldBody);
            }
            changedPlug.notifyAttach(newBody);
         }

         connected.setBody(newBody);
      }
      return replacedBodies;
   }

   public void edgeRemoved(GraphEdgeChangeEvent<BodyVertex, BodyEdge> event) {
      if (event.getType() != GraphEdgeChangeEvent.EDGE_REMOVED) {
         return;
      }
      BodyVertex first = event.getEdgeSource();
      BodyVertex second = event.getEdgeSource();

      // Possible cases:
      // - both vertices are still in the same connecting set
      // - they are not connected any more...
      // - and the separating vertices have a body attached
      // - and the separating vertices do not have a body attached

      ConnectivityInspector<BodyVertex, BodyEdge> inspector = new ConnectivityInspector<BodyVertex, BodyEdge>(
            graph);
      if (inspector.connectedSetOf(first).contains(second)) {
         // both vertices are still in the same connected set,
         // nothing needs to be done
         return;
      } else {
         // the vertices have been disconnected, shallow clone the attached body
         // and replace all body references in the connected set of one of the
         // vertices.
         assert first.getBody() == second.getBody();
         if (first.getBody() != null) {
            RigidBody clone = first.getBody().shallowClone();
            replaceBodiesOnConnected(second, clone);
         }
      }
   }
}
