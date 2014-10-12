package org.codecranachan.asteroidpush;

import java.util.Collection;

import org.codecranachan.asteroidpush.simulation.PhysicsEngine;
import org.codecranachan.asteroidpush.simulation.Scenario;
import org.codecranachan.asteroidpush.simulation.Simulation;
import org.codecranachan.asteroidpush.simulation.Timekeeper;
import org.codecranachan.asteroidpush.visuals.Representation;

public class GameInstance {
   private Timekeeper time;
   private Simulation simulation;

   public GameInstance(PhysicsEngine engine) {
      simulation = new Simulation(engine);
      time = new Timekeeper(engine.getTimeStep());
   }

   public void addRealTime(int milliseconds) {
      time.addRealTime(milliseconds);
      simulation.stepToFrame(time.getGameTime());
   }

   public void installScenario(Scenario scenario) {
      scenario.setUpSimulation(simulation);
   }
   
   public Collection<Representation> getRepresentations() {
      return simulation.getRepresentations();
   }
}
