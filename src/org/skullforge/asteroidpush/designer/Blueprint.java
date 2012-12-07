package org.skullforge.asteroidpush.designer;

import java.util.Collection;

import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Grid;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class Blueprint {
   private Grid<ModuleToken> grid;

   public Blueprint() {
      grid = new Grid<ModuleToken>();
   }

   public void addModule(Placement placement, ModuleData data) {
      if (canAddModule(placement, data)) {
         ModuleToken token = new ModuleToken(data);
         token.setPlacement(placement);
         grid.put(placement.getCoordinate(), token);
      }
   }

   public boolean canAddModule(Placement placement, ModuleData data) {
      if (grid.get(placement.getCoordinate()) != null) {
         return false;
      } else {
         return true;
      }
   }

   public Collection<ModuleToken> getTokens() {
      return grid.getAll();
   }
}
