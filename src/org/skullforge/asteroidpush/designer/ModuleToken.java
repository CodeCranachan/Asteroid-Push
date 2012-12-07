package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ModuleToken {
   private ModuleData data;
   private Placement placement;

   public ModuleToken(ModuleData data) {
      this.data = data;
      this.placement = new Placement();
   }

   public ModuleData getData() {
      return data;
   }

   public Placement getPlacement() {
      return placement;
   }

   public void setPlacement(Placement placement) {
      this.placement.set(placement);
   }
}
