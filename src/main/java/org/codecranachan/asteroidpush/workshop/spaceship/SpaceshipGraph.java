package org.codecranachan.asteroidpush.workshop.spaceship;

import java.util.Map;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;

class Hardlink {

}

class HardlinkEdgeFactory implements EdgeFactory<Hardpoint, Hardlink> {

   public Hardlink createEdge(Hardpoint arg0, Hardpoint arg1) {
      return new Hardlink();
   }

}

public class SpaceshipGraph extends AbstractBaseGraph<Hardpoint, Hardlink>
      implements UndirectedGraph<Hardpoint, Hardlink> {
   private static final long serialVersionUID = 7101689298369513833L;

   private Map<OrthogonalCoordinate, Hardpoint> mesh;

   public SpaceshipGraph() {
      super(new HardlinkEdgeFactory(), false, false);
   }

   public void attachHardpoint(Hardpoint hardpoint,
                               OrthogonalCoordinate coordinate) {
      Hardpoint hook;
      if (mesh.containsKey(coordinate)) {
         hook = mesh.get(coordinate);
      } else {
         hook = new Hardpoint();
         mesh.put(coordinate, hook);
         addVertex(hook);
      }
      if (!containsVertex(hardpoint))
         addVertex(hardpoint);
      addEdge(hardpoint, hook);
   }
}
