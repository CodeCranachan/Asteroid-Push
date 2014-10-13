package org.codecranachan.asteroidpush.base.workshop.actor;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

public class BodyGraph extends ListenableUndirectedGraph<BodyVertex, BodyEdge> {
   private static final long serialVersionUID = -4988861593033174467L;

   public BodyGraph() {
      super(new BaseBodyGraph());
   }
}

class BodyEdgeFactory implements EdgeFactory<BodyVertex, BodyEdge> {
   public BodyEdge createEdge(BodyVertex arg0, BodyVertex arg1) {
      return new BodyEdge();
   }
}

class BaseBodyGraph extends AbstractBaseGraph<BodyVertex, BodyEdge> implements
      UndirectedGraph<BodyVertex, BodyEdge> {

   private static final long serialVersionUID = 4401294643948834459L;
   private static final boolean allowMultipleEdges = false;
   private static final boolean allowLoops = false;

   public BaseBodyGraph() {
      super(new BodyEdgeFactory(), allowMultipleEdges, allowLoops);
   }

}
