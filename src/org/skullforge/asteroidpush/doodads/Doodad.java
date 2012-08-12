package org.skullforge.asteroidpush.doodads;

import java.util.ArrayList;

import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.parts.Part;

/**
 * Groups Parts, Logic and Appearances together.
 * 
 * @author Konfuzzyus
 * 
 */
public class Doodad {
   /**
    * Create a new empty Doodad.
    */
   public Doodad() {
      parts = new ArrayList<Part>();
      partsSpawned = false;
   }

   /**
    * Add a Part to this Doodad. The Part must be unique to this Doodad and may
    * not be used in other Doodads. Parts that are added to a Doodad that has
    * already been spawned are not spawned until the Doodad is respawned.
    * 
    * @param part
    *           the part that will be added to the Doodad.
    */
   public void addPart(Part part) {
      parts.add(part);
   }

   /**
    * Put all parts into the simulation.
    */
   public void spawn(World world) {
      for (Part part : parts) {
         part.spawn(world);
      }
      partsSpawned = true;
   }

   /**
    * Remove all Parts from the simulation.
    */
   public void despawn(World world) {
      for (Part part : parts) {
         part.despawn(world);
      }
      partsSpawned = false;
   }

   /**
    * Updates the Doodad's inner state.
    * 
    * @param frameNumber
    *           the number of the simulation frame. Can be used to compute
    *           delays and other time based logic.
    */
   public void update(int frameNumber) {
   }

   public boolean isSpawned() {
      return this.partsSpawned;
   }

   private ArrayList<Part> parts;
   private boolean partsSpawned;
}
