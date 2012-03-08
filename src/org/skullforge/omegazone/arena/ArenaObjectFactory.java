package org.skullforge.omegazone.arena;

import org.newdawn.slick.SlickException;

public interface ArenaObjectFactory {
  ArenaObject createVessel() throws SlickException;
}
