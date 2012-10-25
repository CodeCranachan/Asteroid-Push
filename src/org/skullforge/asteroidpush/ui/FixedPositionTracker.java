package org.skullforge.asteroidpush.ui;

import org.jbox2d.common.Vec2;

public class FixedPositionTracker implements PositionTracker {

   public FixedPositionTracker() {
      this.center = new Vec2(0.0f, 0.0f);
      this.radius = 80.0f;
   }

   @Override
   public Vec2 getCenter() {
      return center;
   }

   @Override
   public float getRadius() {
      return radius;
   }

   public void setCenter(Vec2 center) {
      this.center.set(center);
   }

   public void setRadius(float radius) {
      this.radius = radius;
   }

   private Vec2 center;
   private float radius;
}
