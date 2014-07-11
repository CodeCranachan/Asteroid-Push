package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public interface ActorFactory {
   void setBodyFactory(RigidBodyFactory factory);

   Actor createActor(Arrow location);
}
