package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Angle {
   public static final Angle PI = new Angle(MathUtils.PI);
   public static final Angle HALF_PI = new Angle(MathUtils.HALF_PI);

   private float angle;

   public static Angle fromDeg(float deg) {
      return new Angle(deg * MathUtils.DEG2RAD);
   }

   public static Angle fromRad(float rad) {
      return new Angle(rad);
   }

   public Angle() {
      this.angle = 0f;
   }

   public Angle(Angle other) {
      this.angle = other.rad();
   }

   private Angle(float angle) {
      this.angle = angle;
   }

   public void set(Angle other) {
      this.angle = other.rad();
   }

   public void setFromRad(float rad) {
      this.angle = rad;
   }

   public void setFromDeg(float deg) {
      this.angle = deg * MathUtils.DEG2RAD;
   }

   public float rad() {
      return angle;
   }

   public float deg() {
      return angle * MathUtils.RAD2DEG;
   }

   public Angle add(Angle other) {
      return new Angle(this.angle + other.angle);
   }

   public Angle sub(Angle other) {
      return new Angle(this.angle - other.angle);
   }

   public Angle mul(float factor) {
      return new Angle(this.angle * factor);
   }

   public Vec2 rotate(Vec2 vector) {
      Transform rot = new Transform();
      rot.set(new Vec2(), angle);
      return Transform.mul(rot, vector);
   }
}
