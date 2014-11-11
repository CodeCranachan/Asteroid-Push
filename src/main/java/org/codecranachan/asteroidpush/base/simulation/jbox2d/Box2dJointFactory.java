package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import org.codecranachan.asteroidpush.base.simulation.DistanceJointData;
import org.codecranachan.asteroidpush.base.simulation.DynamicJoint;
import org.codecranachan.asteroidpush.base.simulation.DynamicJointFactory;
import org.codecranachan.asteroidpush.base.simulation.PrismaticJointData;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.PrismaticJointDef;

public class Box2dJointFactory implements DynamicJointFactory {
   private World world;

   public Box2dJointFactory(World world) {
      assert (world != null);
      this.world = world;
   }

   public DynamicJoint createPrismaticJoint(RigidBody bodyA,
                                            RigidBody bodyB,
                                            PrismaticJointData data) {
      assert bodyA != null;
      assert bodyB != null;
      assert data != null;

      PrismaticJointDef def = new PrismaticJointDef();
      def.bodyA = ((Box2dBody) bodyA).getBox2dBody();
      def.bodyB = ((Box2dBody) bodyB).getBox2dBody();

      def.localAnchorA.set(data.getAnchorA());
      def.localAnchorB.set(data.getAnchorB());
      Vec2 axis = data.getAnchorB().sub(data.getAnchorA());
      float distance = axis.normalize();

      def.localAxisA.set(axis);
      def.collideConnected = true;
      def.referenceAngle = 0.0f;

      def.enableLimit = true;
      def.lowerTranslation = data.getMinLength() - distance;
      def.upperTranslation = data.getMaxLength() - distance;

      def.enableMotor = false;
      def.motorSpeed = 0.0f;
      def.maxMotorForce = 0.0f;

      return new Box2dJoint(world, def);
   }

   public DynamicJoint createDistanceJoint(RigidBody bodyA,
                                           RigidBody bodyB,
                                           DistanceJointData data) {
      assert bodyA != null;
      assert bodyB != null;
      assert data != null;

      DistanceJointDef def = new DistanceJointDef();
      def.bodyA = ((Box2dBody) bodyA).getBox2dBody();
      def.bodyB = ((Box2dBody) bodyB).getBox2dBody();

      def.localAnchorA.set(data.getAnchorA());
      def.localAnchorB.set(data.getAnchorB());

      def.collideConnected = true;
      def.dampingRatio = data.getDampingRatio();
      def.frequencyHz = data.getFrequency();
      def.length = data.getLength();

      return new Box2dJoint(world, def);
   }

}
