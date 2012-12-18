package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.designer.catalogue.ModuleCatalogue;

public class DesignerUiFactory {

   public DesignerUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public void init(Player localPlayer) {
      this.localPlayer = localPlayer;
   }

   public Widget createUi() {
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);

      DesignerLayout ui = new DesignerLayout();
      ui.setBlueprintWidget(new BlueprintDisplayView(localPlayer.getShipDesign()));
      ui.setCatalogueWidget(new ShipModuleList(new ModuleCatalogue(), font));
      return ui;
   }

   private ResourceLoader loader;
   private Player localPlayer;
}
