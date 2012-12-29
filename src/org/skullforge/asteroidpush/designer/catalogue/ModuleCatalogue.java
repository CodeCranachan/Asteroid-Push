package org.skullforge.asteroidpush.designer.catalogue;

import java.util.ArrayList;
import java.util.Collection;

import org.skullforge.asteroidpush.designer.data.ModuleData;

public class ModuleCatalogue {
   ArrayList<ModuleData> availableModules;

   public ModuleCatalogue() {
      availableModules = new ArrayList<ModuleData>();
      populate();
   }

   public Collection<ModuleData> getModuleData() {
      return availableModules;
   }

   private void populate() {
      availableModules.add(SteamThrusterFactory.createData());
      availableModules.add(MetalBlockFactory.createData());
      availableModules.add(MetalWedgeFactory.createData());
      availableModules.add(ClockworkSpinnerFactory.createData());
      availableModules.add(MetalSpikeFactory.createData());
      availableModules.add(ProjectileGunFactory.createData());
   }
}
