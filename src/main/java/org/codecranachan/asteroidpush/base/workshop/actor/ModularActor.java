package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.Collection;
import java.util.Set;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.MathUtils;

public class ModularActor implements Actor {
   private ActorSkeleton skeleton;

   public ModularActor(ActorSkeleton skeleton) {
      this.skeleton = skeleton;
   }

   public Collection<Command> update(int frameNumber) {
      return skeleton.update(frameNumber);
   }

   public Collection<Representation> getRepresentations() {
      return skeleton.getRepresentations();
   }

   public Arrow getFocus() {
      Set<RigidBody> bodies = skeleton.getBodies();
      Arrow focus = new Arrow();
      for (RigidBody body : bodies) {
         focus = new Arrow(body.getPosition().getTail(), MathUtils.HALF_PI,
               25.0f);
      }
      return focus;
   }

   public void destroy() {
      // TODO Detach all behaviors from their bodies and destroy bodies
   }

}
