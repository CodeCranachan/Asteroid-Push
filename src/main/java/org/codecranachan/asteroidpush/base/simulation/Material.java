package org.codecranachan.asteroidpush.base.simulation;

public enum Material {
   METAL(2500f, 0.8f, 0.1f),
   RUBBER(1250f, 0.9f, 0.9f);

   public float density;
   public float friction;
   public float restitution;

   private Material(float density, float friction, float restitution) {
      this.density = density;
      this.friction = friction;
      this.restitution = restitution;
   }

}