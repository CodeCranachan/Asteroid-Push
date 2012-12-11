package org.skullforge.asteroidpush;

import java.util.ArrayList;
import java.util.LinkedList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.ui.Renderer;

public class Simulator {
   private LinkedList<SimulatorCommand> commandList;
   private int currentFrameNumber;
   private ArrayList<Entity> entityList;
   private World world;

   public Simulator() {
      entityList = new ArrayList<Entity>();
      commandList = new LinkedList<SimulatorCommand>();
      currentFrameNumber = 0;
      world = new World(new Vec2(0.0f, -9.81f), true);
   }

   public void addEntity(Entity entity) {
      entityList.add(entity);
   }

   public void clear() {
      for (Entity entity : entityList) {
         entity.destroy();
      }
      commandList.clear();
      entityList.clear();
      currentFrameNumber = 0;
   }

   private void computeNextFrame() {
      ++currentFrameNumber;
      updateEntities();
      executeCommands();
      stepWorld();
   }

   private void executeCommands() {
      while (!commandList.isEmpty()) {
         SimulatorCommand command = commandList.pop();
         command.execute(this);
      }
   }

   public int getCurrentFrameNumber() {
      return currentFrameNumber;
   }

   public float getTimeStep() {
      // A bit more than 60 frames per second
      return 0.0165f;
   }

   public void initialize(Scenario scenario) {
      commandList.addAll(scenario.getSetupCommands(world));
   }

   public void render(Renderer renderer) {
      for (Entity entity : entityList) {
         entity.render(renderer);
      }
   }

   public void stepToFrame(int targetFrameNumber) {
      while (currentFrameNumber < targetFrameNumber) {
         computeNextFrame();
      }
   }

   private void stepWorld() {
      final int velocityIterations = 8;
      final int positionIterations = 3;
      world.step(getTimeStep(), velocityIterations, positionIterations);
   }

   private void updateEntities() {
      for (Entity entity : entityList) {
         entity.update(getCurrentFrameNumber());
      }
   }
}
