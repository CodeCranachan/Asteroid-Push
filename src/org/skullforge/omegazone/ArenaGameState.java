package org.skullforge.omegazone;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.skullforge.omegazone.arena.Arena;

public class ArenaGameState extends BasicGameState {

  private Arena arena;

  @Override
  public void init(GameContainer container, StateBasedGame game)
      throws SlickException {
    arena = new Arena();
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g)
      throws SlickException {
    arena.render(g);
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta)
      throws SlickException {
    arena.update(delta);
  }

  @Override
  public int getID() {
    return State.ARENA.getID();
  }

}
