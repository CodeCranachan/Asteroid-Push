package org.codecranachan.asteroidpush.base.simulation.command;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;

public class CreateActorCommand implements Command {
   private Arrow placement;
   private Velocity velocity;
   private ActorFactory factory;

   public CreateActorCommand(Arrow placement, Velocity velocity,
         ActorFactory factory) {
      this.placement = placement;
      this.factory = factory;
      this.velocity = velocity;
   }

   public void execute(Simulation simulation) {
      factory.setBodyFactory(simulation.getBodyFactory());
      Actor actor = factory.createActor(placement, velocity);
      simulation.addActor(actor);
   }
}
