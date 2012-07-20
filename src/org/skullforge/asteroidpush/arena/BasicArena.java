package org.skullforge.asteroidpush.arena;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.skullforge.asteroidpush.arena.viewports.TrackingViewport;

import java.util.LinkedList;

public class BasicArena implements Arena {

   public BasicArena(EntityFactory factory) {
      objectList = new LinkedList<Entity>();
      objectFactory = factory;
      currentView = null;
      timeAccumulator = 0;

      Vec2 gravity = new Vec2(0, 10.0f);
      boolean doSleep = true;
      physicalWorld = new World(gravity, doSleep);
   }

   public void init() throws SlickException {
      Entity vessel = objectFactory.createVessel();
      addObject(vessel, new Vec2(3.0f, 3.0f));
      addObject(objectFactory.createScenery(), new Vec2(0.0f, 0.0f));
      setViewport(new TrackingViewport(vessel));
   }

   public void render(GameContainer container, Graphics g)
         throws SlickException {
      if (currentView == null) {
         renderEmptyView(g);
      } else {
         renderArenaToView(container, g);
      }
   }

   public void update(int delta) {
      advanceSimulation(delta / 1000.0f);
      
      for (Entity object : objectList) {
         object.update(delta);
      }
   }

   public void setViewport(Viewport view) {
      currentView = view;
   }

   public void addObject(Entity object, Vec2 position) {
      objectList.add(object);
      object.spawn(physicalWorld, position);
   }

   public EntityFactory getFactory() {
      return objectFactory;
   }

   private void renderEmptyView(Graphics g) {
      g.drawString("NO VISUAL SIGNAL CONNECTED", 25.0f, 25.0f);
   }

   private void renderArenaToView(GameContainer container, Graphics g) {
      currentView.setGraphics(container, g);
      for (Entity object : objectList) {
         object.render(currentView);
      }
   }
   
   private void advanceSimulation(float delta) {
      final float timeStep = 1.0f/60.0f;
      final int velocityIterations = 8;
      final int positionIterations = 3;
      
      timeAccumulator += delta;
      while (timeAccumulator > timeStep) {
         timeAccumulator -= timeStep;
         physicalWorld.step(timeStep, velocityIterations, positionIterations);
      }
   }

   private LinkedList<Entity> objectList;
   private EntityFactory objectFactory;
   private Viewport currentView;
   private World physicalWorld;
   private float timeAccumulator;
}
