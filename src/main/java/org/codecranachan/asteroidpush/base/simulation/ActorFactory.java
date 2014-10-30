package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.NewtonianState;

public interface ActorFactory {
   void setBodyFactory(RigidBodyFactory factory);

   Actor createActor(NewtonianState initialState);
}
