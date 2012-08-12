package org.skullforge.asteroidpush;

import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.arena.BasicArena;
import org.skullforge.asteroidpush.arena.BasicEntityFactory;
import org.skullforge.asteroidpush.ui.IngameUiFactory;

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
    * @return a freshly assembled game state to be usde for that state id.
    */
   public GameState createGameState(StateInfo stateId) {
      GameState state;

      if (null == stateId) {
         stateId = StateInfo.INVALID;
      }

      switch (stateId) {
      case ARENA:
         state = createArenaGameState();
         break;
      case MATCH:
         state = createMatchGameState();
         break;
      case INVALID:
      default:
         state = null;
         break;
      }
      return state;
   }

   private ArenaGameState createArenaGameState() {
      BasicArena arena = new BasicArena(new BasicEntityFactory(), new IngameUiFactory());
      return (new ArenaGameState(arena));
   }

   private MatchGameState createMatchGameState() {
      return new MatchGameState(new Simulator());
   }
}
