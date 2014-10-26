package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;

public interface RigidBodyFactory {
   public RigidBody createDynamicBody(Arrow offset, Velocity velocity);

   public RigidBody createStaticBody(Arrow offset);
}
