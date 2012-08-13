package org.skullforge.asteroidpush.doodads;

import java.util.ArrayList;

import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.appearances.Appearance;
import org.skullforge.asteroidpush.parts.Part;
import org.skullforge.asteroidpush.ui.Renderer;

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
   public Doodad(String name) {
      doodadName = new String(name);
      parts = new ArrayList<Part>();
      partsSpawned = false;
      appearances = new ArrayList<Appearance>();
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
    * Adds an Appearance to this Doodad. The appearance should be based on Parts
    * and Logic that has previously been added to the Doodad.
    * 
    * @param appearance
    *           the appearance that will be added to the Doodad.
    */
   public void addAppearance(Appearance appearance) {
      appearances.add(appearance);
   }

   /**
    * Passes all Appearances contained in this Doodad to the renderer given.
    * 
    * @param renderer
    *           the renderer to use to visualize this Doodad.
    */
   public void render(Renderer renderer) {
      for(Appearance a : appearances) {
         renderer.draw(a);
      }
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

   public String getName() {
      return doodadName;
   }

   private String doodadName;
   private ArrayList<Part> parts;
   private boolean partsSpawned;
   private ArrayList<Appearance> appearances;
}
