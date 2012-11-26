package org.skullforge.asteroidpush.assemblies;

import java.util.Collection;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

/**
 * Interface for individual parts of simulated objects.
 * 
 * @author Konfuzzyus
 * 
 */
public interface Assembly {
   /**
    * Create all simulated bodies and fixtures in the world given. The bodies
    * are created at the origin of the World coordinate system and in rest.
    * 
    * @param world
    *           a jbox2d World object to create the bodies in.
    */
   void spawn(World world);

   /**
    * Remove all simulated bodies and fixtures from the world given.
    * 
    * @param world
    *           a jbox2d World object to remove the bodies from.
    */
   void despawn(World world);

   /**
    * Get all jbox2d Body objects attached to this part.
    * 
    * @return a vector of Body objects, is empty if the Part has not been
    *         spawned yet.
    */
   Collection<Body> getBodies();
   
   /**
    * Get all jbox2d Joint objects attached to this part.
    * 
    * @return a vector of Joint objects, is empty if the Part has not been
    *         spawned yet.
    */
   Collection<Joint> getJoints();
}
