package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.Vec2;

public class Circle {
   private Vec2 center;
   private float radius;

   public Circle() {
      this.center = new Vec2();
      this.radius = 0;
   }

   public Circle(Vec2 center, float radius) {
      this.center = center;
      this.radius = radius;
   }

   public Vec2 getCenter() {
      return center;
   }

   public float getRadius() {
      return radius;
   }

   public Circle addRadius(float delta) {
      return new Circle(center, this.radius += delta);
   }
}
