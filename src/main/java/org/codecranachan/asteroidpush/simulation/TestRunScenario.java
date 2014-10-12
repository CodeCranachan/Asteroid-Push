package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.codecranachan.asteroidpush.workshop.assembly.SpaceshipFactory;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class TestRunScenario implements Scenario {
   private SpaceshipFactory testFactory;

   public TestRunScenario(Blueprint prototype) {
      this.testFactory = new SpaceshipFactory(prototype.getPlan(), 1.0f);
   }

   public void setUpSimulation(Simulation simulation) {
      simulation.clear();

      testFactory.setBodyFactory(simulation.getBodyFactory());
      simulation.addActor(testFactory.createActor(new Arrow(new Vec2(0, 0),
            MathUtils.HALF_PI)));
   }
}