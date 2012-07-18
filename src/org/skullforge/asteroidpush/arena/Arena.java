package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Arena {
  public void init() throws SlickException;
  public void setViewport(Viewport view);
  public void render(GameContainer container, Graphics g) throws SlickException;
  public void update(int delta);
  public void addObject(Entity object);
}
