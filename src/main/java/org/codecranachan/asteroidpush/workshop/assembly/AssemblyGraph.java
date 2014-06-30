package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.Map;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;

class RigidConnectorFactory implements EdgeFactory<AssemblyVertex, RigidConnector> {

   public RigidConnector createEdge(AssemblyVertex arg0, AssemblyVertex arg1) {
      return new RigidConnector();
   }

}

public class AssemblyGraph extends AbstractBaseGraph<AssemblyVertex, RigidConnector>
      implements UndirectedGraph<AssemblyVertex, RigidConnector> {
   private static final long serialVersionUID = 7101689298369513833L;

   private Map<OrthogonalCoordinate, AssemblyVertex> mesh;

   public AssemblyGraph() {
      super(new RigidConnectorFactory(), false, false);
   }

   public void attachHardpoint(AssemblyVertex hardpoint,
                               OrthogonalCoordinate coordinate) {
      AssemblyVertex hook;
      if (mesh.containsKey(coordinate)) {
         hook = mesh.get(coordinate);
      } else {
         hook = new AssemblyVertex();
         mesh.put(coordinate, hook);
         addVertex(hook);
      }
      if (!containsVertex(hardpoint))
         addVertex(hardpoint);
      addEdge(hardpoint, hook);
   }
}
