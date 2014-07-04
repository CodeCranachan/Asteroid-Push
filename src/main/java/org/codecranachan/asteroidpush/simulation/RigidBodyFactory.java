package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface RigidBodyFactory {
   public RigidBody createBody(Arrow offset);
}
