package org.codecranachan.asteroidpush.simulation;

import org.jbox2d.common.Vec2;

public interface RigidBodyFactory {
   public RigidBody createBody(Vec2 offset);
}
