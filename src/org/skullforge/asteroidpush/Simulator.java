package org.skullforge.asteroidpush;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.doodads.Doodad;

/**
 * Encapsulates the jbox2d World and all the nifty stuff required to compute the
 * dynamics of the game.
 * 
 * @author Konfuzzyus
 * 
 */
public class Simulator {

   public Simulator() {
      doodadList = new ArrayList<Doodad>();
      currentFrameNumber = 0;
      world = new World(new Vec2(), true);
   }

   /**
    * Adds a Doodad to the simulation. Doodads can not be removed from the
    * outside. To get rid of the Doodad it must be scheduled for destruction by
    * the Doodad itself. The simulation will take care of the rest.
    * 
    * @param doodad
    *           the Doodad to add
    */
   public void addDoodad(Doodad doodad) {
      doodadList.add(doodad);
   }

   /**
    * Steps the Simulation until it reaches the given frame.
    * 
    * @param targetFrameNumber
    *           the simulation will stop stepping and return when its internal
    *           frame counter reaches this number.
    */
   public void stepToFrame(int targetFrameNumber) {
      while (currentFrameNumber < targetFrameNumber) {
         computeNextFrame();
      }
   }

   private void computeNextFrame() {
      ++currentFrameNumber;
      updateDoodads();
      stepWorld();
   }

   private void stepWorld() {
      final float timeStep = 1.0f / 60.0f;
      final int velocityIterations = 8;
      final int positionIterations = 3;
      world.step(timeStep, velocityIterations, positionIterations);
   }

   private void updateDoodads() {
      // Copy the doodadList so there is no weird behavior if a Doodad is added
      // during Doodad::update().
      ArrayList<Doodad> listCopy = new ArrayList<Doodad>(doodadList);
      for (Doodad doodad : listCopy) {
         doodad.update(currentFrameNumber);
      }
   }

   /**
    * Getter for the current simulation frame number. This number counts the
    * simulation steps taken since the start of the simulation.
    * 
    * @return an integer containing the current frame number.
    */
   public int getCurrentFrameNumber() {
      return currentFrameNumber;
   }

   private ArrayList<Doodad> doodadList;
   private int currentFrameNumber;
   private World world;
}
