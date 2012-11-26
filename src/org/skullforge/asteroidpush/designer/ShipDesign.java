package org.skullforge.asteroidpush.designer;

import java.util.Collection;

import org.skullforge.asteroidpush.designer.grid.GlueMap;
import org.skullforge.asteroidpush.designer.grid.Grid;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;

public class ShipDesign {
   public ShipDesign() {
      moduleLayer = new Grid<Module>();
      glueMap = new GlueMap();
   }

   public Collection<Module> getModules() {
      return moduleLayer.getModules();
   }

   public void addModule(Placement placement, ModuleData data) {
      if (canAddModule(placement)) {
         Module module = new Module(data);
         module.setPlacement(placement);
         moduleLayer.put(placement.getCoordinate(), module);
      }
   }

   public boolean canAddModule(Placement placement) {
      if (moduleLayer.get(placement.getCoordinate()) != null) {
         return false;
      } else {
         return true;
      }
   }
   
   public Collection<Collection<SubModule>> getBodyGroups() {
      updateGlueMap();
      return glueMap.getGlueGroups();
   }

   private void updateGlueMap() {
      glueMap.clear();
      for (Module module : moduleLayer.getModules()) {
         Placement place = module.getPlacement();
         for (SubModule sub : module.getSubModules()) {
            glueMap.putPart(place, sub);
         }
      }
   }

   private Grid<Module> moduleLayer;
   private GlueMap glueMap;
}
