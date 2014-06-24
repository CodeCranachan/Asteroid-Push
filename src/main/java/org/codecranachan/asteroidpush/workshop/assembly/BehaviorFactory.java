package org.codecranachan.asteroidpush.workshop.assembly;

import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface BehaviorFactory {
   public void attachBehavior(RigidBody body, Arrow placement);
}
