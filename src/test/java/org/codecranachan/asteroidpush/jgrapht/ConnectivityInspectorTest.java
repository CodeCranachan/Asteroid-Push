//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.jgrapht;

import static org.junit.Assert.*;

import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.junit.*;

public class ConnectivityInspectorTest {
   TestGraph graph;
   ConnectivityInspector<TestVertex, TestEdge> inspector;
   ListenableGraph<TestVertex, TestEdge> listenable;

   @Before
   public void setUp() throws Exception {
      graph = new TestGraph();
      inspector = new ConnectivityInspector<TestVertex, TestEdge>(graph);
      listenable = new ListenableUndirectedGraph<TestVertex, TestEdge>(graph);
      listenable.addGraphListener(inspector);
   }

   @Test
   public void afterAddingNewVertexAllSetsGetRecreated()
         throws Exception {
      TestVertex first = new TestVertex();
      listenable.addVertex(first);

      Set<TestVertex> pivotSet = inspector.connectedSetOf(first);
      listenable.addVertex(new TestVertex());

      assertNotSame(pivotSet, inspector.connectedSetOf(first));
      assertEquals(pivotSet, inspector.connectedSetOf(first));
   }

   @Test
   public void addingUnconnectedVerticesCreatesNewSets() throws Exception {
      TestVertex first = new TestVertex();
      TestVertex second = new TestVertex();
      listenable.addVertex(first);
      listenable.addVertex(second);
      assertNotSame(inspector.connectedSetOf(first),
                    inspector.connectedSetOf(second));
      assertNotEquals(inspector.connectedSetOf(first),
                      inspector.connectedSetOf(second));
   }

   @Test
   public void combiningVertices() throws Exception {
      TestVertex first = new TestVertex();
      TestVertex second = new TestVertex();
      listenable.addVertex(first);
      listenable.addVertex(second);
      Set<TestVertex> firstSet = inspector.connectedSetOf(first);
      Set<TestVertex> secondSet = inspector.connectedSetOf(second);
      listenable.addEdge(first, second);
      assertEquals(inspector.connectedSetOf(first),
                   inspector.connectedSetOf(second));
      assertTrue(inspector.connectedSetOf(first).containsAll(firstSet)
            && inspector.connectedSetOf(first).containsAll(secondSet));
      assertNotSame(inspector.connectedSetOf(first),
                    inspector.connectedSetOf(second));
      assertNotSame(inspector.connectedSetOf(first), firstSet);
      assertNotSame(inspector.connectedSetOf(second), secondSet);
   }

}

class TestVertex {
}

class TestEdge {
}

class TestEdgeFactory implements EdgeFactory<TestVertex, TestEdge> {
   public TestEdge createEdge(TestVertex arg0, TestVertex arg1) {
      return new TestEdge();
   }
}

class TestGraph extends AbstractBaseGraph<TestVertex, TestEdge> implements
      UndirectedGraph<TestVertex, TestEdge> {
   private static final long serialVersionUID = 2210513204408070221L;

   public TestGraph() {
      super(new TestEdgeFactory(), false, false);
   }
}