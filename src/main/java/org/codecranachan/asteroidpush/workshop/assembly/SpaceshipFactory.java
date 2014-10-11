package org.codecranachan.asteroidpush.workshop.assembly;

import java.awt.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.simulation.ActorFactory;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.simulation.modular.ActorSkeleton;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;
import org.codecranachan.asteroidpush.simulation.modular.BodyGraph;
import org.codecranachan.asteroidpush.simulation.modular.BodyVertex;
import org.codecranachan.asteroidpush.simulation.modular.ModularActor;
import org.codecranachan.asteroidpush.simulation.modular.Plug;
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
      this.blueprint = blueprint;
      this.bodyFactory = null;
      this.gridSize = gridSize;
   }

   public void setBodyFactory(RigidBodyFactory factory) {
      assert (factory != null);
      this.bodyFactory = factory;
   }


   public Actor createActor(Arrow placement) {
      assert (bodyFactory != null);
      ModularActor ship = new ModularActor();
      
      ActorSkeleton skeleton = assembleSkeleton();

      return ship;
   }

   /**
    * Builds a new ActorSkeleton from the given Blueprint.
    */
   private ActorSkeleton assembleSkeleton() {
      ActorSkeleton skeleton = new ActorSkeleton();
      for (Token token : blueprint.getTokens()) {
         attachToken(skeleton, token);
      }
      return skeleton;
   }

   private void attachToken(ActorSkeleton skeleton, Token token) {
      Part part = (Part) token.getData();
      Placement placement = token.getPlacement();
      for (BehaviorFactory factory : part.getFactories()) {
         Behavior behavior = factory
               .createBehavior(computeNodePlacement(placement));

         int index = 0;
         for (Socket socket : factory.getSockets()) {
            BodyVertex node = new BodyVertex();
            node.addPlug(new Plug(behavior, index));
            Collection<OrthogonalCoordinate> links = transformLinks(socket.getLinks(),
                                                                    placement);
            skeleton.insertVertex(node, links);
            index++;
         }
      }
   }

   private Collection<OrthogonalCoordinate> transformLinks(Collection<OrthogonalCoordinate> links,
                                                           Placement placement) {
      Collection<OrthogonalCoordinate> transformed = new LinkedList<OrthogonalCoordinate>();
      for (OrthogonalCoordinate link : links) {
         transformed.add(computeConnectorCoordinate(placement, link));
      }
      return transformed;
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
}