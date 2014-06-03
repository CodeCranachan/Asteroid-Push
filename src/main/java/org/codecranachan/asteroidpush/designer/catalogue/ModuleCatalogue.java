//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.designer.catalogue;

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.designer.data.ModuleData;

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
      availableModules.add(DamperFactory.createData());
      availableModules.add(ProjectileGunFactory.createData());
   }
}
