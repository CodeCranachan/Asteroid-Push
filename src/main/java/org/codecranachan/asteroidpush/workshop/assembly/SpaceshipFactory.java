package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.simulation.ActorFactory;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;
import org.codecranachan.asteroidpush.simulation.modular.BodyGraph;
import org.codecranachan.asteroidpush.simulation.modular.BodyVertex;
import org.codecranachan.asteroidpush.simulation.modular.ModularActor;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.workshop.tokenboard.Placement;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.Subgraph;

public class SpaceshipFactory implements ActorFactory {
   private Board blueprint;
   private RigidBodyFactory bodyFactory;
   private float gridSize;
   private AssemblyGraph skeleton;

   public SpaceshipFactory(Board blueprint, float gridSize) {
      assert (blueprint != null);
      assert (bodyFactory != null);
      this.blueprint = blueprint;
      this.bodyFactory = null;
      this.gridSize = gridSize;
      this.skeleton = assembleSkeleton();
   }

   public void setBodyFactory(RigidBodyFactory factory) {
      assert (factory != null);
      this.bodyFactory = factory;
   }

   /**
    * Rebuild the assembly skeleton from a token board.
    * 
    * Will iterate through all tokens on the token board and create nodes for
    * each socket in a behavior factory. The resulting set of nodes is then
    * connected by adding edges from the nodes to the skeleton base grid
    * according to the individual links specified in each socket.
    */
   private AssemblyGraph assembleSkeleton() {
      AssemblyGraph skeleton = new AssemblyGraph();
      for (Token token : blueprint.getTokens()) {
         Part part = (Part) token.getData();
         Placement placement = token.getPlacement();
         for (BehaviorFactory factory : part.getFactories()) {
            int index = 0;
            for (Socket socket : factory.getSockets()) {
               AssemblyVertex node = new AssemblyVertex();
               node.bindFactory(factory, index);
               node.setPlacement(computeNodePlacement(placement));
               for (OrthogonalCoordinate link : socket.getLinks()) {
                  OrthogonalCoordinate plug = computeConnectorCoordinate(placement,
                                                                         link);
                  skeleton.attachHardpoint(node, plug);
               }
               index++;
            }
         }
      }
      return skeleton;
   }

   private OrthogonalCoordinate computeConnectorCoordinate(Placement placement,
                                                           OrthogonalCoordinate relativeCoordinate) {
      OrthogonalCoordinate absoluteCoordinate = new OrthogonalCoordinate(
            relativeCoordinate);
      absoluteCoordinate.turn(placement.getOrientation());
      absoluteCoordinate.move(placement.getPivotCoordinate().getX() * 2,
                              placement.getPivotCoordinate().getY() * 2);
      return absoluteCoordinate;
   }

   private Arrow computeNodePlacement(Placement placement) {
      float quarter_turns_per_revolution = 4f;
      float orientation = (float) placement.getOrientation();
      float angle = orientation * MathUtils.TWOPI
            / quarter_turns_per_revolution;

      OrthogonalCoordinate loc = placement.getPivotCoordinate();
      Vec2 origin = new Vec2((float) loc.getX(), (float) loc.getY());
      origin.mulLocal(gridSize);

      return new Arrow(origin, angle, gridSize);
   }

   public Actor createActor(Arrow placement) {
      assert (bodyFactory != null);
      ModularActor ship = new ModularActor();

      ConnectivityInspector<AssemblyVertex, RigidConnector> inspector = new ConnectivityInspector<AssemblyVertex, RigidConnector>(
            skeleton);

      // Map to associate created nodes with assembly nodes
      Map<AssemblyVertex, BodyVertex> nodeToBodyMap = new HashMap<AssemblyVertex, BodyVertex>();
      Map<BehaviorFactory, Vector<BodyVertex>> bodyBindings = new HashMap<BehaviorFactory, Vector<BodyVertex>>();

      // Create a rigid body for each connected set
      for (Set<AssemblyVertex> rigidSet : inspector.connectedSets()) {
         Subgraph<AssemblyVertex, RigidConnector, AssemblyGraph> subSkeleton = new Subgraph<AssemblyVertex, RigidConnector, AssemblyGraph>(
               skeleton, rigidSet);
         RigidBody body = bodyFactory.createBody(placement);
         BodyGraph bodyGraph = new BodyGraph();

         // Create vertices on body graph
         for (AssemblyVertex assemblyNode : subSkeleton.vertexSet()) {
            BodyVertex bodyNode = new BodyVertex(assemblyNode.getPlacement());
            nodeToBodyMap.put(assemblyNode, bodyNode);
            bodyGraph.addVertex(bodyNode);

            // Store assembled bindings in map
            for (AssemblyBinding binding : assemblyNode.getBindings()) {
               Vector<BodyVertex> boundVertices;
               if (bodyBindings.containsKey(binding.getFactory())) {
                  boundVertices = bodyBindings.get(binding.getFactory());
               } else {
                  boundVertices = new Vector<BodyVertex>();
               }
               boundVertices.set(binding.getIndex(), bodyNode);
               bodyBindings.put(binding.getFactory(), boundVertices);
            }
         }

         // Create edges on body graph
         for (RigidConnector connector : subSkeleton.edgeSet()) {
            AssemblyVertex nodeA = subSkeleton.getEdgeSource(connector);
            AssemblyVertex nodeB = subSkeleton.getEdgeTarget(connector);
            bodyGraph.addEdge(nodeToBodyMap.get(nodeA),
                              nodeToBodyMap.get(nodeB));
         }

         ship.addBody(body, bodyGraph);
      }

      // Create behaviors
      for (Entry<BehaviorFactory, Vector<BodyVertex>> entry : bodyBindings.entrySet()) {
         Behavior behavior = entry.getKey().createBehavior(entry.getValue());
         ship.addBehavior(behavior);
      }

      return ship;
   }
}