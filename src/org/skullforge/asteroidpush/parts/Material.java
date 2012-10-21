package org.skullforge.asteroidpush.parts;

public enum Material {
   METAL(2500.0f, 0.8f, 0.1f);

   final public float density;
   final public float friction;
   final public float restitution;

   Material(float density, float friction, float restitution) {
      this.density = density;
      this.friction = friction;
      this.restitution = restitution;
   }
}
