package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.Force;
import org.codecranachan.asteroidpush.utils.NewtonianState;

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
   NewtonianState getState();

   /**
    * TODO document this better
    * 
    * When there are messages passed to the hull (collisions, damage, etc.)
    * relay them to the given interaction handler
    */
   void addHull(Hull hull, InteractionHandler handler);

   void removeHull(Hull hull);

   /**
    * Apply a force to the rigid body. The force has to be supplied in the
    * Body's coordinate system.
    * 
    * @param force
    *           The force to be applied.
    */
   void applyForce(Force force);

   /**
    * Transforms a force given in body coordinates to world coordinates.
    * 
    * @param force
    *           the force to be transformed.
    * @return A new instance containing the transformed force.
    */
   Force transformToWorld(Force force);

   /**
    * Transforms a force given in body coordinates to world coordinates..
    * 
    * @param force
    *           the force to be transformed.
    * @return A new instance containing the transformed force.
    */
   NewtonianState transformToWorld(NewtonianState state);

   void applyTorque(float torque);

   Circle getEnclosingCircle();

   // register collision handler...
}
