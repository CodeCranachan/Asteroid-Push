package org.skullforge.omegazone;

import org.skullforge.omegazone.ArenaGameState;
import org.skullforge.omegazone.arena.BasicArena;
import org.skullforge.omegazone.arena.Arena;
import org.skullforge.omegazone.arena.BasicArenaObjectFactory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class OmegaZone extends StateBasedGame {

  public OmegaZone() {
    super("OmegaZone");
  }

  @Override
  public void initStatesList(GameContainer container) throws SlickException {
    Arena arena = new BasicArena(new BasicArenaObjectFactory());
    addState(new ArenaGameState(arena));
  }
}
