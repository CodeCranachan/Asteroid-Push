package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.visuals.Representation;

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
