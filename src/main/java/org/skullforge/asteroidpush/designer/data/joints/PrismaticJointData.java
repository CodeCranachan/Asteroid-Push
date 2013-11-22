package org.skullforge.asteroidpush.designer.data.joints;

import org.jbox2d.common.Rot;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.skullforge.asteroidpush.entities.spaceship.Part;

public class PrismaticJointData extends BasicJointData {

   private boolean collideConnected;
   
   public PrismaticJointData() {
      this.collideConnected = true;
   }

   public void setCollideConnected(boolean collideConnected) {
      this.collideConnected = collideConnected;
   }

   @Override
   public JointDef generateJointDef(Part first, Part second, float moduleSize) {

      PrismaticJointDef def = new PrismaticJointDef();
      def.bodyA = first.getBody();
      def.bodyB = second.getBody();
      
      Transform firstTransform = first.getPlacement().getTransform();
      Vec2 firstAnchor = new Vec2();
      Rot.mulTrans(firstTransform.q, getFirst().anchor, firstAnchor);
      firstAnchor.addLocal(firstTransform.p);
      firstAnchor.mulLocal(moduleSize);
      def.localAnchorA.set(firstAnchor);

      Transform secondTransform = second.getPlacement().getTransform();
      Vec2 secondAnchor = new Vec2();
      Rot.mulTrans(secondTransform.q, getSecond().anchor, secondAnchor);
      firstAnchor.addLocal(firstTransform.p);
      secondAnchor.mulLocal(moduleSize);
      def.localAnchorB.set(secondAnchor);

      def.localAxisA.set(secondAnchor.sub(firstAnchor));
      def.collideConnected = this.collideConnected;
      def.enableLimit = false;
      def.enableMotor = false;
      def.lowerTranslation = -1.0f * moduleSize;
      def.upperTranslation = 1.0f * moduleSize;
      def.maxMotorForce = 1.0f;
      def.motorSpeed = 0.0f;

      return def;
   }
}
