package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.LinkedList;

public class BasicArena implements Arena {

  public BasicArena(EntityFactory factory) {
    objectList = new LinkedList<Entity>();
    objectFactory = factory;
    currentView = null;
  }

  public void init() throws SlickException {
    addObject(objectFactory.createVessel());
    setViewport(new StaticViewport());
  }

  public void render(GameContainer container, Graphics g) throws SlickException {
    if (currentView == null) {
      renderEmptyView(g);
    }
    else {
      renderArenaToView(container, g);
    }
  }

  public void update(int delta) {
    for (Entity object : objectList) {
      object.update(delta);
    }
  }
  
  public void setViewport(Viewport view) {
    currentView = view;
  }

  public void addObject(Entity object) {
    objectList.add(object);
  }
  
  public EntityFactory getFactory() {
    return objectFactory;
  }
  
  private void renderEmptyView(Graphics g) {
    g.drawString("NO VISUAL SIGNAL CONNECTED", 25.0f, 25.0f);
  }

  private void renderArenaToView(GameContainer container, Graphics g) {
    currentView.setGraphics(g);
    for (Entity object : objectList) {
      object.render(currentView);
    }
  }
  
  private LinkedList<Entity> objectList;
  private EntityFactory objectFactory;
  private Viewport currentView;
}
