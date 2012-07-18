package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.LinkedList;

public class BasicArena implements Arena {

  public BasicArena(EntityFactory factory) {
    objectList = new LinkedList<Entity>();
    objectFactory = factory;
  }

  public void init() throws SlickException {
    addObject(objectFactory.createVessel());
  }

  public void render(Graphics g) throws SlickException {
    Viewport view = new StaticViewport(g);
    for (Entity object : objectList) {
      object.render(view);
    }
  }

  public void update(int delta) {
    for (Entity object : objectList) {
      object.update(delta);
    }
  }

  public void addObject(Entity object) {
    objectList.add(object);
  }
  
  public EntityFactory getFactory() {
    return objectFactory;
  }

  private LinkedList<Entity> objectList;
  private EntityFactory objectFactory;
}
