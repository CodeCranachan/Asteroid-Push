package org.skullforge.asteroidpush.designer;

import java.util.Collection;

import org.skullforge.asteroidpush.designer.grid.ModuleGrid;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ShipDesign {
   public ShipDesign() {
      moduleLayer = new ModuleGrid();
   }

   public Collection<Module> getModules() {
      return moduleLayer.getModules();
   }

   public void addModule(Placement placement, Module module) {
      if (canAddModule(placement, module)) {
         moduleLayer.addModule(placement, module);
      }
   }

   public boolean canAddModule(Placement placement, Module module) {
      if (moduleLayer.getModule(placement.getCoordinate()) != null) {
         return false;
      } else {
         return true;
      }
   }

   private ModuleGrid moduleLayer;
}
