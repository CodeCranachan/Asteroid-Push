package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Box2dFactory implements RigidBodyFactory {
   private World world;

   public Box2dFactory(World world) {
      assert (world != null);
      this.world = world;
   }

   public RigidBody createDynamicBody(Arrow offset) {
      BodyDef def = getBasicBodyDef();

      def.position.set(offset.getTail());
      def.angle = offset.getAngle();
      def.type = BodyType.DYNAMIC;

      return new Box2dBody(world, def);
   }

   public RigidBody createStaticBody(Arrow offset) {
      BodyDef def = getBasicBodyDef();

      def.position.set(offset.getTail());
      def.angle = offset.getAngle();
      def.type = BodyType.STATIC;

      return new Box2dBody(world, def);
   }

   private BodyDef getBasicBodyDef() {
      BodyDef def = new BodyDef();

      def.linearDamping = 0.05f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;

      return def;
   }
}
