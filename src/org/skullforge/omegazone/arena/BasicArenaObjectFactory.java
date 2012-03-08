package org.skullforge.omegazone.arena;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicArenaObjectFactory implements ArenaObjectFactory {

  @Override
  public ArenaObject createVessel() throws SlickException {
    return new Vessel(new Image("res/Defiant.png"));
  }

}
