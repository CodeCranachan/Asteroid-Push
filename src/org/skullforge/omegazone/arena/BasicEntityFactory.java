package org.skullforge.omegazone.arena;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.skullforge.omegazone.arena.entities.Vessel;

public class BasicEntityFactory implements EntityFactory {

  @Override
  public Entity createVessel() throws SlickException {
    return new Vessel(new Image("shuttle.png"));
  }

}
