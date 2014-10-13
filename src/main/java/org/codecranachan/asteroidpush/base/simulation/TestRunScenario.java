package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.codecranachan.asteroidpush.content.actors.ScenarioBorderFactory;
import org.codecranachan.asteroidpush.content.actors.SpaceshipFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class TestRunScenario implements Scenario {
   private SpaceshipFactory testFactory;
   private ScenarioBorderFactory borderFactory;

   public TestRunScenario(Blueprint prototype) {
      this.testFactory = new SpaceshipFactory(prototype.getPlan(), 1.0f);
      this.borderFactory = new ScenarioBorderFactory(150.0f, 100.0f);
   }

   public void setUpSimulation(Simulation simulation) {
      RigidBodyFactory bodyFactory = simulation.getBodyFactory();
      simulation.clear();

      borderFactory.setBodyFactory(bodyFactory);
      simulation.addActor(borderFactory.createActor(new Arrow()));

      testFactory.setBodyFactory(bodyFactory);
      simulation.addActor(testFactory.createActor(new Arrow(new Vec2(0, 0),
            MathUtils.HALF_PI)));
   }
}