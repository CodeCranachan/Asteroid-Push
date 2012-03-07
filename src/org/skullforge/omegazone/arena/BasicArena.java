package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import java.util.LinkedList;

public class BasicArena implements Arena {

  public BasicArena() {
    objectList = new LinkedList<ArenaObject>();
  }
  
  public void render(Graphics g) {
    for(ArenaObject object : objectList) {
      object.render(g);
    }
  }

  public void update(int delta) {
    for(ArenaObject object : objectList) {
      object.update(delta);
    }
  }
  
  public void addObject(ArenaObject object) {
    objectList.add(object);
  }

  private LinkedList<ArenaObject> objectList;

}
