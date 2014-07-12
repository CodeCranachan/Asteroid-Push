//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.legacy.entities.Entity;
import org.codecranachan.asteroidpush.legacy.ui.Renderer;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Simulator {
   private LinkedList<SimulatorCommand> commandList;
   private int currentFrameNumber;
   private ArrayList<Entity> entityList;
   private World world;

   public Simulator() {
      entityList = new ArrayList<Entity>();
      commandList = new LinkedList<SimulatorCommand>();
      currentFrameNumber = 0;
      world = new World(new Vec2(0.0f, -9.81f));
      world.setContactListener(new CollisionObserver());
   }

   public void addEntity(Entity entity) {
      entityList.add(entity);
   }

   public void removeEntity(Entity entity) {
      entityList.remove(entity);
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
         Collection<SimulatorCommand> commands = entity
               .update(getCurrentFrameNumber());
         if (commands != null)
            commandList.addAll(commands);
      }
   }
}
