package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class AsteroidPush extends StateBasedGame {

  public AsteroidPush(StateFactory factory) {
    super("Asteroid Push");
    stateFactory = factory;
  }

  @Override
  public void initStatesList(GameContainer container) throws SlickException {
    addState(stateFactory.createArenaState());
  }
  
  private StateFactory stateFactory;
}
