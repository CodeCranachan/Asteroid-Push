package org.skullforge.omegazone.arena;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicEntityFactory implements EntityFactory {

  @Override
  public Entity createVessel() throws SlickException {
    return new Vessel(new Image("res/Defiant.png"));
  }

}
