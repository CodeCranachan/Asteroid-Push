package org.codecranachan.asteroidpush.base.simulation.command;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.utils.NewtonianState;

public class CreateActorCommand implements Command {
   private NewtonianState initialState;
   private ActorFactory factory;

   public CreateActorCommand(NewtonianState initialState,
         ActorFactory factory) {
      this.initialState = initialState;
      this.factory = factory;
   }

   public void execute(Simulation simulation) {
      factory.setBodyFactory(simulation.getBodyFactory());
      Actor actor = factory.createActor(initialState);
      simulation.addActor(actor);
   }
}
