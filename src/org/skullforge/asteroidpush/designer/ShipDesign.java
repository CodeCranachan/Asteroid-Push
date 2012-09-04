package org.skullforge.asteroidpush.designer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShipDesign {
   public ShipDesign() {
      moduleLayer = new HashMap<GridCoordinate, Module>();
   }

   public Collection<Module> getModules() {
      return moduleLayer.values();
   }

   public void addModule(GridCoordinate position, Module module) {
      if (canAddModule(position, module)) {
         moduleLayer.put(position, module);
         module.setPosition(position);
      }
   }

   public boolean canAddModule(GridCoordinate position, Module module) {
      if (moduleLayer.containsKey(position)) {
         return false;
      } else {
         return true;
      }
   }

   private Map<GridCoordinate, Module> moduleLayer;
}
