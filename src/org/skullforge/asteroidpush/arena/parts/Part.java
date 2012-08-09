package org.skullforge.asteroidpush.arena.parts;

import java.util.ArrayList;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Interface for individual parts of simulated objects.
 * 
 * @author christianmeyer
 *
 */
public interface Part {
   /**
    * Create all simulated bodies and fixtures in the world given.
    * The bodies are created at the origin of the World coordinate system and in rest.
    * 
    * @param world a jbox2d World object to create the bodies in.
    */
   void spawn(World world);
   
   /**
    * Remove all simulated bodies and fixtures from the world given.
    * 
    * @param world a jbox2d World object to remove the bodies from.
    */
   void despawn(World world);
   
   /**
    * Get all jbox2d Body objects attached to this part.
    * 
    * @return a vector of Body objects, is empty if the Part has not been spawned yet.
    */
   ArrayList<Body> getBodies();
}
