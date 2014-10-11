package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

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

}
