package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.Balancing;
import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.codecranachan.asteroidpush.content.actors.SpaceshipFactory;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.FieldOfView;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.common.Vec2;

public class ShipPrototypeRule implements Rule {
   private ActorFactory prototypeFactory;
   private Actor prototype;
   private Controller controller;

   public ShipPrototypeRule(Blueprint blueprint, Controller controller) {
      this.prototypeFactory = new SpaceshipFactory(blueprint.getPlan(),
            Balancing.getShipSizeFactor());
      this.prototype = null;
      this.controller = controller;
   }

   public FieldOfView getFieldOfView() {
      if (prototype == null) {
         return new FieldOfView(new Circle(new Vec2(0f, 0f), 5f), Angle.HALF_PI);
      } else {
         return prototype.getFieldOfView();
      }
   }

   public void update(Simulation simulation, int frame) {
      if (prototype == null) {
         prototypeFactory.setBodyFactory(simulation.getBodyFactory());
         prototype = prototypeFactory.createActor(getSpawnState());
         prototype.setController(controller);
         simulation.addActor(prototype);
      }
   }

   private NewtonianState getSpawnState() {
      NewtonianState initial = new NewtonianState();
      initial.setState(new Vec2(0, 0), Angle.HALF_PI);
      initial.setVelocity(new Vec2(0, 0), new Angle());
      return initial;
   }
}
