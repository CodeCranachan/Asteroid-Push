package org.codecranachan.asteroidpush.base.simulation.command;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.utils.Arrow;

public class CreateActorCommand implements Command {
   private Arrow placement;
   private ActorFactory factory;

   public CreateActorCommand(Arrow placement, ActorFactory factory) {
      this.placement = placement;
      this.factory = factory;
   }

   public void execute(Simulation simulation) {
      factory.setBodyFactory(simulation.getBodyFactory());
      Actor actor = factory.createActor(placement);
      simulation.addActor(actor);
   }
}
