package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Box2dFactory implements RigidBodyFactory {
   private World world;

   public Box2dFactory(World world) {
      assert (world != null);
      this.world = world;
   }

   public RigidBody createDynamicBody(NewtonianState initial_state) {
      BodyDef def = getBasicBodyDef();

      def.position.set(initial_state.getPosition());
      def.angle = initial_state.getRotation().rad();
      def.linearVelocity = initial_state.getLinearVelocity();
      def.angularVelocity = initial_state.getAngularVelocity().rad();
      def.type = BodyType.DYNAMIC;

      return new Box2dBody(world, def);
   }

   public RigidBody createStaticBody(NewtonianState initial_state) {
      BodyDef def = getBasicBodyDef();

      def.position.set(initial_state.getPosition());
      def.angle = initial_state.getRotation().rad();
      def.linearVelocity.set(initial_state.getLinearVelocity());
      def.angle = initial_state.getAngularVelocity().rad();
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
