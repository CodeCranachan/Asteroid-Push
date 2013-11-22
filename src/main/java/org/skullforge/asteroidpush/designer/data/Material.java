package org.skullforge.asteroidpush.designer.data;

public enum Material {
   METAL(2500.0f, 0.8f, 0.1f),
   RUBBER(500.0f, 0.9f, 0.9f);

   final public float density;
   final public float friction;
   final public float restitution;

   Material(float density, float friction, float restitution) {
      this.density = density;
      this.friction = friction;
      this.restitution = restitution;
   }
}
