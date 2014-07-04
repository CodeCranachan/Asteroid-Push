package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface RigidBody {
   // When there are messages passed to the hull (collisions, damage, etc.)
   // relay them to the given interaction handler
   void addHull(Hull hull, InteractionHandler handler);

   void removeHull(Hull hull);

   void applyForce(Arrow force);

   void applyTorque(float torque);

   // register collision handler...
}
