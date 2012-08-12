package org.skullforge.asteroidpush;

import java.util.ArrayList;

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
         stepSimulation();
      }
   }

   /**
    * Advances the simulation by a single frame.
    */
   private void stepSimulation() {
      ++currentFrameNumber;
      for (Doodad doodad : doodadList) {
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
}
