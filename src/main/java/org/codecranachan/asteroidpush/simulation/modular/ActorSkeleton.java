package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class ActorSkeleton {
   private BodyGraph graph;
   private Map<OrthogonalCoordinate, BodyVertex> mesh;

   // something to store body <-> subgraph associations

   public ActorSkeleton() {
      graph = new BodyGraph();
      mesh = new HashMap<OrthogonalCoordinate, BodyVertex>();
   }

   public void plugVertex(BodyVertex addedVertex,
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
}
