package org.codecranachan.asteroidpush.state;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestRunContext implements StateContext {
   private int previousStateId;

   public TestRunContext(Blueprint prototype) {

   }

   public void enterContext(AsteroidPush game) {
      previousStateId = game.getCurrentStateID();
      game.enterState(StateId.SIMULATION, new FadeOutTransition(Color.black,
            250), new FadeInTransition(Color.black, 250));
   }

   public void exitContext(AsteroidPush game) {
      game.enterState(previousStateId,
                      new FadeOutTransition(Color.black, 250),
                      new FadeInTransition(Color.black, 250));
   }

}
