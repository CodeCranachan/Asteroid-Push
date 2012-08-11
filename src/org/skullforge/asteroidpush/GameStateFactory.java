package org.skullforge.asteroidpush;

import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.arena.BasicArena;
import org.skullforge.asteroidpush.arena.BasicEntityFactory;

public class GameStateFactory {

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
      BasicArena arena = new BasicArena(new BasicEntityFactory());
      return (new ArenaGameState(arena));
   }

   private MatchGameState createMatchGameState() {
      return new MatchGameState();
   }
}
