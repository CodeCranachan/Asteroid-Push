package org.codecranachan.asteroidpush.simulation.command;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.simulation.ActorFactory;
import org.codecranachan.asteroidpush.simulation.Simulation;
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
