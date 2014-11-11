package org.codecranachan.asteroidpush.base.simulation;

public interface PhysicsEngine {
   public RigidBodyFactory getBodyFactory();

   public void stepWorld();

   public float getTimeStep();
}
