package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.grid.Placement;

public abstract class BaseModule implements Module {
   public BaseModule() {
      placement = new Placement();
   }

   @Override
   public Placement getPlacement() {
      return placement;
   }

   @Override
   public void setPlacement(Placement placement) {
      this.placement.set(placement);
   }

   final private Placement placement;
}
