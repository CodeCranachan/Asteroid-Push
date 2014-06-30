package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.EntityFactory;
import org.codecranachan.asteroidpush.simulation.Behavior;
import org.codecranachan.asteroidpush.simulation.Constraint;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.spaceship.BodyGraph;
import org.codecranachan.asteroidpush.workshop.spaceship.BodyVertex;
import org.codecranachan.asteroidpush.workshop.spaceship.SpaceshipEntity;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.workshop.tokenboard.Placement;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.Subgraph;

public class SpaceshipFactory implements EntityFactory {
   private Board<Part> blueprint;
   private RigidBodyFactory bodyFactory;
   private float gridSize;
   private AssemblyGraph skeleton;

   public SpaceshipFactory(Board<Part> blueprint, RigidBodyFactory bodyFactory,
         float gridSize) {
      assert (blueprint != null);
      assert (bodyFactory != null);
      this.blueprint = blueprint;
      this.bodyFactory = bodyFactory;
      this.gridSize = gridSize;
      this.skeleton = assembleSkeleton();
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

   public Entity createEntity(Vec2 location) {
      SpaceshipEntity ship = new SpaceshipEntity();

      ConnectivityInspector<AssemblyVertex, RigidConnector> inspector = new ConnectivityInspector<AssemblyVertex, RigidConnector>(
            skeleton);

      // Map to associate created nodes with assembly nodes
      Map<AssemblyVertex, BodyVertex> nodeToBodyMap = new HashMap<AssemblyVertex, BodyVertex>();

      // Create a rigid body for each connected set
      for (Set<AssemblyVertex> rigidSet : inspector.connectedSets()) {
         Subgraph<AssemblyVertex, RigidConnector, AssemblyGraph> subSkeleton = new Subgraph<AssemblyVertex, RigidConnector, AssemblyGraph>(
               skeleton, rigidSet);
         RigidBody body = bodyFactory.createBody(location);

         BodyGraph bodyGraph = new BodyGraph();

         // Create vertices on body graph
         for (AssemblyVertex assemblyNode : subSkeleton.vertexSet()) {
            BodyVertex bodyNode = new BodyVertex();
            nodeToBodyMap.put(assemblyNode, bodyNode);
            bodyGraph.addVertex(bodyNode);
            for (BehaviorFactory factory : assemblyNode.getBehaviors()) {
               Behavior behavior = factory.createBehavior(assemblyNode
                     .getPlacement());
               body.addBehavior(behavior, bodyNode);
            }
         }

         // Create edges on body graph
         for (RigidConnector connector : subSkeleton.edgeSet()) {
            AssemblyVertex nodeA = subSkeleton.getEdgeSource(connector);
            AssemblyVertex nodeB = subSkeleton.getEdgeTarget(connector);
            bodyGraph.addEdge(nodeToBodyMap.get(nodeA),
                              nodeToBodyMap.get(nodeB));
         }

         body.setGraph(bodyGraph);
         ship.addBody(body);
      }

      // Create constraints
      for (Token<Part> token : blueprint.getTokens()) {
         Part part = token.getData();
         for (DynamicConnector connector : part.getSoftlinks()) {
            AssemblyVertex nodeA = connector.getNodeA();
            AssemblyVertex nodeB = connector.getNodeB();
            ConstraintFactory factory = connector.getConstraintFactory();
            Constraint constraint = factory.createConstraint(nodeToBodyMap
                  .get(nodeA), nodeToBodyMap.get(nodeB));
            ship.addConstraint(constraint);
         }
      }

      return ship;
   }
}