package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import org.codecranachan.asteroidpush.base.Balancing;
import org.codecranachan.asteroidpush.base.simulation.PhysicsEngine;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Box2dEngine implements PhysicsEngine {
   private World world;

   public Box2dEngine() {
      Vec2 gravity = new Vec2(0, -Balancing.getDefaultGravity());
      this.world = new World(gravity);
   }

   public RigidBodyFactory getBodyFactory() {
      return new Box2dBodyFactory(world);
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
