package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.Vec2;

public class FieldOfView {
   private Circle area;
   private Angle up;

   public FieldOfView() {
      this.area = new Circle(new Vec2(), 1.0f);
      this.up = Angle.fromRad(0);
   }

   public FieldOfView(Circle area, Angle up) {
      this.area = area;
      this.up = up;
   }

   public Vec2 getCenter() {
      return area.getCenter();
   }

   public Angle getUp() {
      return up;
   }

   public float getRadius() {
      return area.getRadius();
   }
}
