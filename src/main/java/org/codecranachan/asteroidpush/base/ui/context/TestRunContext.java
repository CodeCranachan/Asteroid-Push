package org.codecranachan.asteroidpush.base.ui.context;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.base.GameInstance;
import org.codecranachan.asteroidpush.base.scenario.Player;
import org.codecranachan.asteroidpush.base.scenario.TestRunScenario;
import org.codecranachan.asteroidpush.base.ui.StateContext;
import org.codecranachan.asteroidpush.base.ui.StateId;
import org.codecranachan.asteroidpush.base.workshop.Blueprint;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestRunContext implements StateContext {
   private int previousStateId;
   private TestRunScenario scenario;
   private GameInstance previousGame;

   public TestRunContext(Player testPilot, Blueprint prototype) {
      scenario = new TestRunScenario(testPilot, prototype);
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
