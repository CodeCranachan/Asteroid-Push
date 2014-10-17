package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class ActorSkeleton {
   private BodyGraph graph;
   private Map<OrthogonalCoordinate, BodyVertex> mesh;
   private BodyAssociationManager bodyManager;

   public ActorSkeleton() {
      graph = new BodyGraph();
      mesh = new HashMap<OrthogonalCoordinate, BodyVertex>();
      bodyManager = new BodyAssociationManager(graph);
      graph.addGraphListener(bodyManager);
   }

   /**
    * Delegates the update callback to all behaviors attached to the skeleton.
    * 
    * Warning: This will only trigger behaviors that have bodies plugged on
    * index 0. At the time, this seemed the easiest way to prevent behaviors
    * getting updated twice in a single frame.
    * 
    * @param frame
    *           The current simulation frame number
    */
   public Collection<Command> update(int frame) {
      Collection<Command> commands = new LinkedList<Command>();
      for (BodyVertex vertex : graph.vertexSet()) {
         for (Plug plug : vertex.getPlugs()) {
            if (plug.getIndex() == 0) {
               commands.addAll(plug.getBehavior().update(frame));
            }
         }
      }
      return commands;
   }

   /**
    * Retrieves the representations of all behaviors attached to the skeleton.
    * 
    * @return A collection of representations
    */
   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      for (BodyVertex vertex : graph.vertexSet()) {
         for (Plug plug : vertex.getPlugs()) {
            if (plug.getIndex() == 0) {
               representations.addAll(plug.getBehavior().getRepresentations());
            }
         }
      }
      return representations;
   }

   public void insertVertex(BodyVertex addedVertex,
                            Collection<OrthogonalCoordinate> links) {
      assert addedVertex != null;
      assert links != null;
      graph.addVertex(addedVertex);
      for (OrthogonalCoordinate item : links) {
         BodyVertex linkVertex;
         if (mesh.containsKey(item)) {
            linkVertex = mesh.get(item);
         } else {
            linkVertex = new BodyVertex();
            mesh.put(item, linkVertex);
            graph.addVertex(linkVertex);
         }
         graph.addEdge(addedVertex, linkVertex);
      }
   }

   public void spawnBodies(Arrow offset, RigidBodyFactory factory) {
      bodyManager.spawnMissingBodies(offset, factory);
   }
   
   
   public Set<RigidBody> getBodies() {
      Set<RigidBody> bodies = new HashSet<RigidBody>();
      for (BodyVertex vertex : graph.vertexSet()) {
         bodies.add(vertex.getBody());
      } 
      return bodies;
   }

}
