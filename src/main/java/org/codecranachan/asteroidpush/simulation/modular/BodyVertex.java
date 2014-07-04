package org.codecranachan.asteroidpush.simulation.modular;

import org.codecranachan.asteroidpush.utils.Arrow;

public class BodyVertex {
   private Arrow placement;

   public BodyVertex(Arrow placement) {
      assert (placement != null);
      this.placement = placement;
   }

   public Arrow getPlacement() {
      return placement;
   }
}
