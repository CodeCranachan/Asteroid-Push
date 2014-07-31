package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.utils.Arrow;

public class Hull {
   private Arrow offset;
   private Primitive shape;
   private Material material;

   public Hull(Arrow offset, Primitive shape, Material material) {
      assert (shape != null);
      assert (material != null);
      this.offset = offset;
      this.shape = shape;
      this.material = material;
   }
   
   public Arrow getOffset() {
      return offset;
   }

   public Primitive getShape() {
      return shape;
   }

   public Material getMaterial() {
      return material;
   }
}