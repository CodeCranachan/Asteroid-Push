package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Arena {
  public void init() throws SlickException;
  public void render(Graphics g) throws SlickException;
  public void update(int delta);
  public void addObject(ArenaObject object);
}
