package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.codecranachan.asteroidpush.content.actors.ScenarioBorderFactory;
import org.codecranachan.asteroidpush.utils.Arrow;

public class TestRunScenario implements Scenario {
   private Blueprint prototype;
   private ScenarioBorderFactory borderFactory;
   private Player testPilot;

   public TestRunScenario(Player testPilot, Blueprint prototype) {
      assert testPilot != null;
      assert prototype != null;
      this.prototype = prototype;
      this.testPilot = testPilot;
      this.borderFactory = new ScenarioBorderFactory(150.0f, 100.0f);
   }

   public void setUp(Simulation simulation, Referee referee) {
      RigidBodyFactory bodyFactory = simulation.getBodyFactory();
      simulation.clear();
      referee.clear();

      // Create the map
      borderFactory.setBodyFactory(bodyFactory);
      Actor border = borderFactory.createActor(new Arrow());
      simulation.addActor(border);

      // Add rules and roles
      testPilot.getController().clearStreams();
      ShipPrototypeRule rule = new ShipPrototypeRule(prototype,
            testPilot.getController());
      referee.addRule(rule);
      referee
            .addParticipant(testPilot,
                            new TestPilotRole(rule, testPilot.getController()));
   }
}