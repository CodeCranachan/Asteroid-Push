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

package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.designer.catalogue.ModuleCatalogue;

public class DesignerUiFactory {

   private ResourceLoader loader;
   private Player localPlayer;
   private BlueprintManipulator manipulator;

   public DesignerUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public Widget createUi() {
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);

      DesignerLayout ui = new DesignerLayout();
      ui.setBlueprintWidget(new BlueprintDisplayView(localPlayer
            .getShipDesign(), manipulator));
      ui.setCatalogueWidget(new ShipModuleList(this.manipulator, new ModuleCatalogue(), font));
      ui.setSelectionWidget(new SelectionView(manipulator));
      return ui;
   }

   public void init(Player localPlayer, BlueprintManipulator manipulator) {
      this.localPlayer = localPlayer;
      this.manipulator = manipulator;
   }
}
