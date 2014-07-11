package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codecranachan.asteroidpush.entities.Entity;
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
   private Board<Part> blueprint;
   private RigidBodyFactory bodyFactory;
   private float gridSize;
   private AssemblyGraph skeleton;

   public SpaceshipFactory(Board<Part> blueprint, float gridSize) {
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

   private AssemblyGraph assembleSkeleton() {
      AssemblyGraph skeleton = new AssemblyGraph();
      for (Token<Part> token : blueprint.getTokens()) {
         Part part = token.getData();
         Placement placement = token.getPlacement();
         for (AssemblyVertex node : part.getHardpoints()) {
            node.setPlacement(computeNodePlacement(placement));
            for (OrthogonalCoordinate relLink : node.getHardLinks()) {
               skeleton.attachHardpoint(node,
                                        computeConnectorCoordinate(placement,
                                                                   relLink));
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

   public Entity createEntity(Vec2 placment) {
      return null;
   }

   public Actor createActor(Arrow placement) {
      assert (bodyFactory != null);
      ModularActor ship = new ModularActor();

      ConnectivityInspector<AssemblyVertex, RigidConnector> inspector = new ConnectivityInspector<AssemblyVertex, RigidConnector>(
            skeleton);

      // Map to associate created nodes with assembly nodes
      Map<AssemblyVertex, BodyVertex> nodeToBodyMap = new HashMap<AssemblyVertex, BodyVertex>();

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
      for (Token<Part> token : blueprint.getTokens()) {
         Part part = token.getData();
         for (BehaviorFactory factory : part.getBehaviors()) {
            // Build list of body nodes
            AbstractList<BodyVertex> bodyVertices = new ArrayList<BodyVertex>();
            for (AssemblyVertex vertex : factory.getNodes()) {
               bodyVertices.add(nodeToBodyMap.get(vertex));
            }

            Behavior behavior = factory.createBehavior(bodyVertices);
            ship.addBehavior(behavior);
         }
      }

      return ship;
   }
}