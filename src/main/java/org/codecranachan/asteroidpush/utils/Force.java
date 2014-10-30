package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.Vec2;

public class Force {
   private Vec2 point;
   private Vec2 vector;

   public Force(Vec2 offset, Vec2 force) {
      this.point = new Vec2(offset);
      this.vector = new Vec2(force);
   }

   public Vec2 getOffset() {
      return point;
   }

   public Vec2 getForce() {
      return vector;
   }

   public Force scaleForce(float factor) {
      return new Force(point, vector.mul(factor));
   }

   public Arrow asArrow() {
      return new Arrow(point, point.add(vector));
   }

   public Force transformBy(Arrow offset) {
      Vec2 point = offset.getTail().add(this.point);
      Vec2 vector = offset.getAngle().rotate(this.vector);
      return new Force(point, vector);
   }

}
