package org.skullforge.asteroidpush.designer.data.joints;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Rot;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.entities.spaceship.Part;

public class DistanceJointData extends BasicJointData {

   private float frequency;
   private float dampingRatio;
   private float length;
   private boolean collideConnected;
   
   public DistanceJointData() {
      this.frequency = 5.0f;
      this.dampingRatio = 0.1f;
      this.length = 1.0f;
      this.collideConnected = true;
   }

   public void setFrequency(float frequency) {
      this.frequency = frequency;
   }

   public void setDampingRatio(float dampingRatio) {
      this.dampingRatio = dampingRatio;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public void setCollideConnected(boolean collideConnected) {
      this.collideConnected = collideConnected;
   }

   @Override
   public JointDef generateJointDef(Part first, Part second, float moduleSize) {

      DistanceJointDef def = new DistanceJointDef();
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
      secondAnchor.addLocal(secondTransform.p);
      secondAnchor.mulLocal(moduleSize);
      def.localAnchorB.set(secondAnchor);

      def.collideConnected = this.collideConnected;
      def.frequencyHz = this.frequency;
      def.dampingRatio = this.dampingRatio;
      def.length = this.length * moduleSize;

      return def;
   }
}
