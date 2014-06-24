package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Arrow {
   private Vec2 origin;
   private float angle;
   private float magnitude;

   public Arrow() {
      this.origin = new Vec2(0, 0);
      this.angle = 0;
      this.magnitude = 1;
   }

   public Arrow(Vec2 origin, float angle) {
      this.origin = new Vec2(origin);
      this.angle = angle;
      this.magnitude = 1f;
   }
   
   public Arrow(Vec2 origin, float angle, float magnitude) {
      this.origin = new Vec2(origin);
      this.angle = angle;
      this.magnitude = magnitude;
   }

   public Arrow(Vec2 tail, Vec2 tip) {
      Vec2 diff = tip.sub(tail);
      this.origin = new Vec2(tail);
      this.magnitude = diff.length();
      if (magnitude == 0) {
         this.angle = 0;
      } else {
         this.angle = (float) Math.asin(diff.y / magnitude);
      }
   }

   public Vec2 getTip() {
      return getDirection().addLocal(origin);
   }

   public Vec2 getTail() {
      return origin;
   }

   public float getAngle() {
      return angle;
   }

   public Vec2 getDirection() {
      return new Vec2(MathUtils.cos(angle), MathUtils.sin(angle))
            .mulLocal(magnitude);
   }

   public Transform getTransform() {
      Transform result = new Transform();
      result.set(origin, angle);
      return result;
   }

   public Arrow applyTransform(Transform transform) {
      float resultAngle = this.angle + transform.q.getAngle();
      Vec2 resultPosition = Transform.mul(transform, origin);
      return new Arrow(resultPosition, resultAngle, magnitude);
   }

   public Arrow applyScale(float scale) {
      return new Arrow(origin.mul(scale), angle, magnitude * scale);
   }
}
