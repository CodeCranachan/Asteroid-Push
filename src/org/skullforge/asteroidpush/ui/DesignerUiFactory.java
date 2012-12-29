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
