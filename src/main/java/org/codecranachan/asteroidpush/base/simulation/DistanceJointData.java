package org.codecranachan.asteroidpush.base.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class DistanceJointData {
   private Vec2 anchorA;
   private Vec2 anchorB;
   float dampingRatio;
   float frequency;
   float length;

   public DistanceJointData() {
      this.anchorA = new Vec2();
      this.anchorB = new Vec2();
      this.frequency = 0f;
      this.dampingRatio = 0f;
      this.length = 0f;
   }

   public DistanceJointData(DistanceJointData other) {
      this.anchorA = new Vec2(other.anchorA);
      this.anchorB = new Vec2(other.anchorB);
      this.frequency = other.frequency;
      this.dampingRatio = other.dampingRatio;
      this.length = other.length;
   }

   public Vec2 getAnchorA() {
      return anchorA;
   }

   public Vec2 getAnchorB() {
      return anchorB;
   }

   public float getDampingRatio() {
      return dampingRatio;
   }

   public float getFrequency() {
      return frequency;
   }

   public float getLength() {
      return length;
   }

   public void setAnchorA(Vec2 anchorA) {
      this.anchorA.set(anchorA);
   }

   public void setAnchorB(Vec2 anchorB) {
      this.anchorB.set(anchorB);
   }

   public void setDampingRatio(float dampingRatio) {
      this.dampingRatio = dampingRatio;
   }

   public void setFrequency(float frequency) {
      this.frequency = frequency;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public void transformBy(Arrow offset) {
      anchorA = Transform.mul(offset.getTransform(), anchorA);
      anchorB = Transform.mul(offset.getTransform(), anchorB);
   }
}