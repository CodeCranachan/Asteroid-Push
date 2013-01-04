package org.skullforge.asteroidpush.utils;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Pointer {
   private float angle;
   private Vec2 position;

   public Pointer() {
      this.angle = 0.0f;
      this.position = new Vec2();
   }

   public Pointer(Vec2 position, float angle) {
      this.angle = angle;
      this.position = new Vec2(position);
   }

   public void set(Vec2 position, float angle) {
      this.angle = angle;
      this.position.set(position);
   }

   public float getAngle() {
      return angle;
   }

   public Vec2 getDirection() {
      return new Vec2(MathUtils.cos(angle), MathUtils.sin(angle));
   }

   public Vec2 getPosition() {
      return position;
   }

   public Transform getTransform() {
      Transform result = new Transform();
      result.set(position, angle);
      return result;
   }

   public Pointer applyTransform(Transform transform) {
      float resultAngle = this.angle + transform.getAngle();
      Vec2 resultPosition = Transform.mul(transform, position);
      return new Pointer(resultPosition, resultAngle);
   }
   
   public Pointer applyScale(float scale) {
      return new Pointer(position.mul(scale), angle);
   }
}
