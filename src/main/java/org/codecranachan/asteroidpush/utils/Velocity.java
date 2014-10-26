package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Velocity {
   private Vec2 linear;
   private float angular;

   public Velocity() {
      this.linear = new Vec2();
      this.angular = 0f;
   }

   public Velocity(Vec2 linear, float angular) {
      this.linear = new Vec2(linear);
      this.angular = angular;
   }

   public Vec2 getLinear() {
      return linear;
   }

   public float getAngular() {
      return angular;
   }

   public Velocity add(Velocity other) {
      return new Velocity(this.getLinear().add(other.getLinear()),
            this.getAngular() + other.getAngular());
   }

   public Velocity rotate(float angle) {
      Transform rotation = new Transform();
      rotation.set(new Vec2(), angle);
      return new Velocity(Transform.mul(rotation, linear), angular);
   }
}
