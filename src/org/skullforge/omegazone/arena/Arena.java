package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import java.util.LinkedList;

public class Arena {

  public Arena() {
    objectList = new LinkedList<ArenaObject>();
  }
  
  public void render(Graphics g) {
    for(ArenaObject object : objectList) {
      object.render(g);
    }
  }

  public void update(int delta) {

  }
  
  public void addObject(ArenaObject object) {
    objectList.add(object);
  }

  private LinkedList<ArenaObject> objectList;

}
