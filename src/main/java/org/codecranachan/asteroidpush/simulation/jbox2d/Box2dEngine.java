package org.codecranachan.asteroidpush.simulation.jbox2d;

import org.codecranachan.asteroidpush.simulation.PhysicsEngine;
import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Box2dEngine implements PhysicsEngine {
   private World world;

   public Box2dEngine() {
      Vec2 gravity = new Vec2(0, -10.0f);
      this.world = new World(gravity);
   }

   public RigidBodyFactory getBodyFactory() {
      return new Box2dFactory(world);
   }

   public void stepWorld() {
      final int velocityIterations = 8;
      final int positionIterations = 3;
      world.step(getTimeStep(), velocityIterations, positionIterations);
   }

   public float getTimeStep() {
      // A bit more than 60 frames per second
      return 0.0165f;
   }

}
