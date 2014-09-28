package org.codecranachan.asteroidpush.simulation.modular;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;

class BodyEdgeFactory implements EdgeFactory<BodyVertex, BodyEdge> {

   public BodyEdge createEdge(BodyVertex arg0, BodyVertex arg1) {
      return new BodyEdge();
   }
}

public class BodyGraph extends AbstractBaseGraph<BodyVertex, BodyEdge>
      implements UndirectedGraph<BodyVertex, BodyEdge> {

   private static final long serialVersionUID = 4401294643948834459L;

   private static final boolean allowMultipleEdges = false;
   private static final boolean allowLoops = false;

   public BodyGraph() {
      super(new BodyEdgeFactory(), allowMultipleEdges, allowLoops);
   }
}
