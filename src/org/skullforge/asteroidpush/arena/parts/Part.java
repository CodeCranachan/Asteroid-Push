package org.skullforge.asteroidpush.arena.parts;

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
   void Spawn(World world);
   
   /**
    * Remove all simulated bodies and fixtures from the world given.
    * 
    * @param world a jbox2d World object to remove the bodies from.
    */
   void Despawn(World world);
}
