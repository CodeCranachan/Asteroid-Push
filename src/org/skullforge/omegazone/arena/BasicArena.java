package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.LinkedList;

public class BasicArena implements Arena {

  public BasicArena(ArenaObjectFactory factory) {
    objectList = new LinkedList<ArenaObject>();
    objectFactory = factory;
  }

  public void init() throws SlickException {
    addObject(objectFactory.createVessel());
  }

  public void render(Graphics g) throws SlickException {
    for (ArenaObject object : objectList) {
      object.render(g);
    }
  }

  public void update(int delta) {
    for (ArenaObject object : objectList) {
      object.update(delta);
    }
  }

  public void addObject(ArenaObject object) {
    objectList.add(object);
  }

  private LinkedList<ArenaObject> objectList;
  private ArenaObjectFactory objectFactory;
}
