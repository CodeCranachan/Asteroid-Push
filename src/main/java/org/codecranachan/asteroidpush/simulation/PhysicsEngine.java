package org.codecranachan.asteroidpush.simulation;

public interface PhysicsEngine {
   public RigidBodyFactory getBodyFactory();

   public void stepWorld();

   public float getTimeStep();
}
