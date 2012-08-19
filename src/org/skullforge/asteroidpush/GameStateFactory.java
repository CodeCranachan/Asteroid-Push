package org.skullforge.asteroidpush;

import org.newdawn.slick.state.GameState;

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
      MatchGameState state = new MatchGameState(new Simulator(), resourceLoader);
      state.setScenario(new Scenario());
      return state;
   }
}
