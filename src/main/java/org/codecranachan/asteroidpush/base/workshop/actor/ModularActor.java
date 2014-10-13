package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;

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

   public void destroy() {
      // TODO Detach all behaviors from their bodies and destroy bodies
   }

}
