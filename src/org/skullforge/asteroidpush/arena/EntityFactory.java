package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.SlickException;

public interface EntityFactory {
  Entity createVessel() throws SlickException;
}
