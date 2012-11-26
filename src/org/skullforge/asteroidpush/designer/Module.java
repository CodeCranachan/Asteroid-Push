package org.skullforge.asteroidpush.designer;

import java.util.ArrayList;
import java.util.Collection;

import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;
import org.skullforge.asteroidpush.designer.modules.data.PartData;

public class Module {
   public String getName() {
      return data.getName();
   }

   public Collection<SubModule> getSubModules() {
      ArrayList<SubModule> subModules = new ArrayList<SubModule>();
      for (PartData part : data.getParts()) {
         subModules.add(new SubModule(placement, part));
      }
      return subModules;
   }

   public Module(ModuleData data) {
      this.data = data;
      this.placement = new Placement();
   }

   public Placement getPlacement() {
      return placement;
   }

   public void setPlacement(Placement placement) {
      this.placement.set(placement);
   }

   final private Placement placement;
   private ModuleData data;
}