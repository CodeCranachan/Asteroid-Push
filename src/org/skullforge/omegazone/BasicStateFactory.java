package org.skullforge.omegazone;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.skullforge.omegazone.arena.Arena;
import org.skullforge.omegazone.arena.BasicArena;
import org.skullforge.omegazone.arena.BasicEntityFactory;

public class BasicStateFactory implements StateFactory {

  public GameState createArenaState() throws SlickException {
    Arena arena = new BasicArena(new BasicEntityFactory());
    return (new ArenaGameState(arena));
  }

}
