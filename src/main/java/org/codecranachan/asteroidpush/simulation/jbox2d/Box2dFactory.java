package org.codecranachan.asteroidpush.simulation.jbox2d;

import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.dynamics.World;

public class Box2dFactory implements RigidBodyFactory {
   private World world;

   public Box2dFactory(World world) {
      assert (world != null);
      this.world = world;
   }

   public RigidBody createBody(Arrow offset) {
      return new Box2dBody(world, offset);
   }

}
