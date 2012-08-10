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
   }

   /**
    * Add a Part to this Doodad. The Part must be unique to this Doodad and may
    * not be used in other Doodads.
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
   }
   
   /**
    * Remove all Parts from the simulation.
    */
   public void despawn(World world) {
      for (Part part : parts) {
         part.despawn(world);
      }
   }

   private ArrayList<Part> parts;
}
