package org.skullforge.asteroidpush;

import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.ui.IngameUiFactory;
import org.skullforge.asteroidpush.ui.UiFactory;
import org.skullforge.asteroidpush.ui.layouts.Layout;
import org.skullforge.asteroidpush.ui.layouts.SimpleLayout;

/**
 * Abstracts game state creation for the game container.
 * 
 * @author Konfuzzyus
 * 
 */
public class GameStateFactory {

   /**
    * Creates a game state for the given state id.
    * 
    * @param stateId
    *           id of the game state
    * @return a freshly assembled game state to be used for that state id.
    */
   public GameState createGameState(StateInfo stateId,
                                    ResourceLoader resourceLoader) {
      GameState state;

      if (null == stateId) {
         stateId = StateInfo.INVALID;
      }

      switch (stateId) {
      case MATCH:
         state = createMatchGameState(resourceLoader);
         break;
      case INVALID:
      default:
         state = null;
         break;
      }
      return state;
   }

   private MatchGameState createMatchGameState(ResourceLoader resourceLoader) {
      Layout ui = new SimpleLayout();
      UiFactory uifactory = new IngameUiFactory(resourceLoader);
      uifactory.createUi(ui);
      return new MatchGameState(new Simulator(), ui);
   }
}
