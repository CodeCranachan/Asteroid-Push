package org.codecranachan.asteroidpush.content.actors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.base.workshop.actor.ActorSkeleton;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.actor.BodyVertex;
import org.codecranachan.asteroidpush.base.workshop.actor.ModularActor;
import org.codecranachan.asteroidpush.base.workshop.actor.Plug;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Placement;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class SpaceshipFactory implements ActorFactory {
   private Board blueprint;
   private RigidBodyFactory bodyFactory;
   private float gridSize;

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

   public Actor createActor(NewtonianState initialState) {
      assert (bodyFactory != null);
      ActorSkeleton skeleton = assembleSkeleton();
      ModularActor ship = new ModularActor(skeleton);
      skeleton.spawnBodies(initialState, bodyFactory);
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

      return new Arrow(origin, Angle.fromRad(angle));
   }
}