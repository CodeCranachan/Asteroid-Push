package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.NewtonianState;

public interface RigidBodyFactory {
   public RigidBody createDynamicBody(NewtonianState initial_state);

   public RigidBody createStaticBody(NewtonianState initial_state);
}
