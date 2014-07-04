package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface RigidBody {
   // When there are messages passed to the primitive (collisions, damage, etc.)
   // relay them to the given interaction handler
   void AddPrimitive(Primitive primitive, InteractionHandler handler);

   void RemovePrimitive(Primitive primitive);

   void ApplyForce(Arrow force);

   void ApplyTorque(float torque);

   // register collision handler...
}
