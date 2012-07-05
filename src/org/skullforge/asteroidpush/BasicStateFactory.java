package org.skullforge.asteroidpush;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.arena.Arena;
import org.skullforge.asteroidpush.arena.BasicArena;
import org.skullforge.asteroidpush.arena.BasicEntityFactory;

public class BasicStateFactory implements StateFactory {

  public GameState createArenaState() throws SlickException {
    Arena arena = new BasicArena(new BasicEntityFactory());
    return (new ArenaGameState(arena));
  }

}
