package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface RigidBody {
   /**
    * Removes the body from the simulation.
    * 
    * References to the body can be safely discarded once this has been called.
    * If you discard body references without calling this there might be
    * inaccessible zombie bodies left in the simulation which consume memory and
    * performance.
    */
   void destroy();

   /**
    * Create a shallow clone of this body.
    * 
    * "Shallow" in this context means that it will not clone any objects that
    * are attached to the Body (Hulls for instance). Shallow clones are used to
    * split a RigidBody into two independent bodies that start with a copy of
    * each other's state vector.
    * 
    * @return shallow clone of this RigidBody
    */
   RigidBody shallowClone();

   /**
    * Fetch the current world coordinates of the simulated body.
    */
   Arrow getPosition();

   /**
    * TODO document this better
    * 
    * When there are messages passed to the hull (collisions, damage, etc.)
    * relay them to the given interaction handler
    */
   void addHull(Hull hull, InteractionHandler handler);

   void removeHull(Hull hull);

   void applyForce(Arrow force);

   void applyTorque(float torque);

   // register collision handler...
}
