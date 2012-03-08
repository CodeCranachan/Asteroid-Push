package org.skullforge.omegazone.arena;

import org.newdawn.slick.SlickException;

public interface EntityFactory {
  Entity createVessel() throws SlickException;
}
