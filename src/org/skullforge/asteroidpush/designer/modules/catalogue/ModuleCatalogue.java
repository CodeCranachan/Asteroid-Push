package org.skullforge.asteroidpush.designer.modules.catalogue;

import java.util.ArrayList;
import java.util.Collection;

import org.skullforge.asteroidpush.designer.modules.data.ModuleData;

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
   }
}
