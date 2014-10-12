package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface RigidBodyFactory {
   public RigidBody createDynamicBody(Arrow offset);
   public RigidBody createStaticBody(Arrow offset);
}
