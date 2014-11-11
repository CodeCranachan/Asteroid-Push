package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Arrow {
   private Vec2 origin;
   private Angle angle;

   public Arrow() {
      this.origin = new Vec2(0, 0);
      this.angle = new Angle();
   }

   public Arrow(Vec2 origin, Angle angle) {
      this.origin = new Vec2(origin);
      this.angle = new Angle(angle);
   }

   public Arrow(Vec2 tail, Vec2 tip) {
      Vec2 diff = tip.sub(tail);
      this.origin = new Vec2(tail);
      float magnitude = diff.length();
      if (magnitude == 0) {
         this.angle = new Angle();
      } else {
         float ang = (float) Math.atan2(diff.y, diff.x);
         this.angle = Angle.fromRad(ang);
      }
   }

   public Vec2 getTip() {
      return getDirection().addLocal(origin);
   }

   public Vec2 getTail() {
      return origin;
   }

   public Angle getAngle() {
      return angle;
   }

   public Vec2 getDirection() {
      return new Vec2(MathUtils.cos(angle.rad()), MathUtils.sin(angle.rad()));
   }

   public Transform getTransform() {
      Transform result = new Transform();
      result.set(origin, angle.rad());
      return result;
   }

   public Arrow applyTransform(Transform transform) {
      Angle resultAngle = Angle.fromRad(transform.q.getAngle()).add(angle);
      Vec2 resultPosition = Transform.mul(transform, origin);
      return new Arrow(resultPosition, resultAngle);
   }

   public Arrow applyTransform(Arrow arrow) {
      return applyTransform(arrow.getTransform());
   }

   public Arrow add(Arrow other) {
      return new Arrow(getTail().add(other.getTail()), getAngle()
            .add(other.getAngle()));
   }
}
