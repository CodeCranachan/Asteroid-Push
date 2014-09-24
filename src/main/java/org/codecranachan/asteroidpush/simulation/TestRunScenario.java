package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.codecranachan.asteroidpush.workshop.assembly.SpaceshipFactory;

public class TestRunScenario {
   private SpaceshipFactory testFactory;

   public TestRunScenario(Blueprint prototype) {
      this.testFactory = new SpaceshipFactory(prototype.getPlan(), 1.0f);
   }

   public void setUpSimulation(Simulation simulation) {
      simulation.clear();

      testFactory.setBodyFactory(simulation.getBodyFactory());
      simulation.addActor(testFactory.createActor(new Arrow()));
   }
}