package org.skullforge.omegazone;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class OmegaZone extends StateBasedGame {

  public OmegaZone(StateFactory factory) {
    super("OmegaZone");
    stateFactory = factory;
  }

  @Override
  public void initStatesList(GameContainer container) throws SlickException {
    addState(stateFactory.createArenaState());
  }
  
  private StateFactory stateFactory;
}
