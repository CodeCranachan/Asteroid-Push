package org.codecranachan.asteroidpush.base.simulation.command;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.Simulation;

public class DestroyActorCommand implements Command {
   private Actor target;

   public DestroyActorCommand(Actor target) {
      this.target = target;
   }

   public void execute(Simulation simulation) {
      simulation.removeActor(target);
   }
}
