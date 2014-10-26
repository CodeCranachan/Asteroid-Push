package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;

public interface ActorFactory {
   void setBodyFactory(RigidBodyFactory factory);

   Actor createActor(Arrow location, Velocity velocity);
}
