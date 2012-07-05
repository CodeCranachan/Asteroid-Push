package org.skullforge.asteroidpush;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

public interface StateFactory {
  GameState createArenaState() throws SlickException;
}
