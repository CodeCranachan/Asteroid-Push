package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.skullforge.asteroidpush.arena.Arena;

public class ArenaGameState extends BasicGameState {

  public ArenaGameState(Arena arena) {
    gameArena = arena;
  }
  
  @Override
  public void init(GameContainer container, StateBasedGame game)
      throws SlickException {
    gameArena.init();
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g)
      throws SlickException {
    gameArena.render(container, g);
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta)
      throws SlickException {
    gameArena.update(delta);
  }

  @Override
  public int getID() {
    return StateInfo.ARENA.getID();
  }
  
  public Arena getArena() {
    return gameArena;
  }
  
  private Arena gameArena;

}
