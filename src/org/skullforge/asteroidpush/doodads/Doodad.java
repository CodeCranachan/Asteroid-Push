package org.skullforge.asteroidpush.doodads;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.appearances.Appearance;
import org.skullforge.asteroidpush.logic.Logic;
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
      partList = new ArrayList<Part>();
      appearances = new ArrayList<Appearance>();
      logicList = new ArrayList<Logic>();
      spawned = false;
      owner = null;
   }

   public Vec2 getCenterOfInterest() {
      Vec2 position = new Vec2(0.0f, 0.0f);
      float numberOfBodies = 0.0f;
      for (Part part : partList) {
         for (Body body : part.getBodies()) {
            position.addLocal(body.getWorldCenter());
            ++numberOfBodies;
         }
      }
      return position.mul(1.0f / numberOfBodies);
   }

   public float getRadiusOfInterest() {
      return 35.0f;
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
      partList.add(part);
   }
   
   /**
    * Add a Logic to this Doodad. The Logic must be unique to this Doodad and may
    * not be used in other Doodads.
    * 
    * @param logic
    *           the logic that will be added to the Doodad.
    */
   public void addLogic(Logic logic) {
      logicList.add(logic);
   }

   /**
    * Put all parts into the simulation.
    */
   public void spawn(World world) {
      for (Part part : partList) {
         part.spawn(world);
      }
      spawned = true;
   }

   /**
    * Remove all Parts from the simulation.
    */
   public void despawn(World world) {
      for (Part part : partList) {
         part.despawn(world);
      }
      spawned = false;
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
      for (Appearance a : appearances) {
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
      for (Logic logic : logicList) {
         if (owner == null) {
            logic.update(frameNumber, null);
         } else {
            logic.update(frameNumber, owner.getController());
         }
      }
   }

   public boolean isSpawned() {
      return this.spawned;
   }

   public String getName() {
      return doodadName;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   private String doodadName;
   private ArrayList<Part> partList;
   private ArrayList<Logic> logicList;
   private ArrayList<Appearance> appearances;
   private boolean spawned;
   private Player owner;
}
