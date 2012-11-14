package org.skullforge.asteroidpush.designer.grid;

import java.util.Collection;
import java.util.HashMap;

import org.skullforge.asteroidpush.designer.Module;

public class ModuleGrid {

   public ModuleGrid() {
      moduleLayer = new HashMap<Coordinate, Module>();
   }

   public Collection<Module> getModules() {
      return moduleLayer.values();
   }

   public void addModule(Placement placement, Module module) {
      moduleLayer.put(placement.getCoordinate(), module);
      module.setPlacement(placement);
   }

   public Module getModule(Coordinate position) {
      return moduleLayer.get(position);
   }

   private HashMap<Coordinate, Module> moduleLayer;
}
