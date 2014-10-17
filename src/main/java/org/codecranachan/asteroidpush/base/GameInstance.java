package org.codecranachan.asteroidpush.base;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.scenario.Player;
import org.codecranachan.asteroidpush.base.scenario.Referee;
import org.codecranachan.asteroidpush.base.scenario.Scenario;
import org.codecranachan.asteroidpush.base.simulation.PhysicsEngine;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.base.simulation.Timekeeper;
import org.codecranachan.asteroidpush.base.ui.simulation.Viewport;
import org.codecranachan.asteroidpush.base.ui.widget.Widget;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public class GameInstance {
   private Timekeeper time;
   private Simulation simulation;
   private Referee referee;

   public GameInstance(PhysicsEngine engine) {
      simulation = new Simulation(engine);
      time = new Timekeeper(engine.getTimeStep());
      referee = new Referee();
   }

   public void addRealTime(int milliseconds) {
      time.addRealTime(milliseconds);
      simulation.stepToFrame(time.getGameTime());
      referee.update(simulation, time.getGameTime());
   }

   public void installScenario(Scenario scenario) {
      scenario.setUp(simulation, referee);
   }

   public Collection<Representation> getRepresentations() {
      return simulation.getRepresentations();
   }

   public Widget getUi(Player player) {
      Viewport playerViewport = referee.getInterfaceFor(player);
      playerViewport.setRepresentations(simulation.getRepresentations());
      return playerViewport;
   }

}