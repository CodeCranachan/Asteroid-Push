package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.Balancing;
import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.codecranachan.asteroidpush.content.actors.SpaceshipFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class ShipPrototypeRule implements Rule {
   private ActorFactory prototypeFactory;
   private Actor prototype;
   private Controller controller;

   public ShipPrototypeRule(Blueprint blueprint, Controller controller) {
      this.prototypeFactory = new SpaceshipFactory(blueprint.getPlan(), Balancing.getShipSizeFactor());
      this.prototype = null;
      this.controller = controller;
   }

   Arrow getFocus() {
      if (prototype == null) {
         return getSpawnLocation();
      } else {
         return prototype.getFocus();
      }
   }

   public void update(Simulation simulation, int frame) {
      if (prototype == null) {
         prototypeFactory.setBodyFactory(simulation.getBodyFactory());
         prototype = prototypeFactory.createActor(getSpawnLocation(), new Velocity());
         prototype.setController(controller);
         simulation.addActor(prototype);
      }
   }

   private Arrow getSpawnLocation() {
      return new Arrow(new Vec2(0, 0), MathUtils.HALF_PI, 1.0f);
   }
}
