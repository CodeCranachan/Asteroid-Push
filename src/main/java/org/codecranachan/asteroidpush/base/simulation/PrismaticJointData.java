package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class PrismaticJointData {
   private Vec2 anchorA;
   private Vec2 anchorB;
   float maxLength;
   float minLength;

   public PrismaticJointData() {
      this.anchorA = new Vec2();
      this.anchorB = new Vec2();
      this.minLength = 0;
      this.maxLength = 0;
   }

   public PrismaticJointData(PrismaticJointData other) {
      this.anchorA = new Vec2(other.anchorA);
      this.anchorB = new Vec2(other.anchorB);
      this.minLength = other.minLength;
      this.maxLength = other.maxLength;
   }

   public void transformBy(Arrow offset, float scale) {
      anchorA = Transform.mul(offset.getTransform(), anchorA.mul(scale));
      anchorB = Transform.mul(offset.getTransform(), anchorB.mul(scale));
      minLength *= scale;
      maxLength *= scale;
   }

   public Vec2 getAnchorA() {
      return anchorA;
   }

   public Vec2 getAnchorB() {
      return anchorB;
   }

   public float getMaxLength() {
      return maxLength;
   }

   public float getMinLength() {
      return minLength;
   }

   public void setAnchorA(Vec2 anchorA) {
      this.anchorA.set(anchorA);
   }

   public void setAnchorB(Vec2 anchorB) {
      this.anchorB.set(anchorB);
   }

   public void setMaxLength(float maxLength) {
      this.maxLength = maxLength;
   }

   public void setMinLength(float minLength) {
      this.minLength = minLength;
   }
}