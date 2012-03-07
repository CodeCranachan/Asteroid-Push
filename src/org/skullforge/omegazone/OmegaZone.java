package org.skullforge.omegazone;

import org.skullforge.omegazone.ArenaGameState;
import org.skullforge.omegazone.arena.BasicArena;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class OmegaZone extends StateBasedGame {

  public OmegaZone() {
    super("OmegaZone");
  }

  @Override
  public void initStatesList(GameContainer container) throws SlickException {
    addState(new ArenaGameState(new BasicArena()));
  }
}
