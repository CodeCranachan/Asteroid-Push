package org.codecranachan.asteroidpush.state;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.GameInstance;
import org.codecranachan.asteroidpush.simulation.TestRunScenario;
import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestRunContext implements StateContext {
   private int previousStateId;
   private TestRunScenario scenario;
   private GameInstance previousGame;

   public TestRunContext(Blueprint prototype) {
      scenario = new TestRunScenario(prototype);
   }

   public void enterContext(AsteroidPush game) {
      previousStateId = game.getCurrentStateID();
      previousGame = game.getActiveGame();
      createTestRun(game);
      game.enterState(StateId.SIMULATION, new FadeOutTransition(Color.black,
            250), new FadeInTransition(Color.black, 250));
   }

   public void exitContext(AsteroidPush game) {
      game.setActiveGame(previousGame);
      game.enterState(previousStateId,
                      new FadeOutTransition(Color.black, 250),
                      new FadeInTransition(Color.black, 250));
   }

   private void createTestRun(AsteroidPush game) {
      GameInstance testRun = new GameInstance(game.createPhysicsEngine());
      testRun.installScenario(scenario);
      game.setActiveGame(testRun);
   }

}
