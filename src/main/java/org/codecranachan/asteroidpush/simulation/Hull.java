package org.codecranachan.asteroidpush.simulation;

public class Hull {
   private Primitive shape;
   private Material material;

   public Hull(Primitive shape, Material material) {
      assert (shape != null);
      assert (material != null);
      this.shape = shape;
      this.material = material;
   }

   public Primitive getShape() {
      return shape;
   }

   public Material getMaterial() {
      return material;
   }
}