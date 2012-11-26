package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.PartData;

public class SubModule {
   public Placement getPlace() {
      return place;
   }

   public PartData getData() {
      return data;
   }

   public SubModule(Placement place, PartData data) {
      this.place = place;
      this.data = data;
   }

   private Placement place;
   private PartData data;
}
