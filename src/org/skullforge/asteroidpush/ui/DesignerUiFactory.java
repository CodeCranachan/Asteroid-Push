package org.skullforge.asteroidpush.ui;

import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ResourceLoader;

public class DesignerUiFactory {

   public DesignerUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public void init(Player localPlayer) {
      this.localPlayer = localPlayer;
   }

   public Widget createUi() {
      DesignerLayout ui = new DesignerLayout();
      ui.setBlueprintWidget(new BlueprintDisplayView(localPlayer.getShipDesign()));
      return ui;
   }

   private ResourceLoader loader;
   private Player localPlayer;
}
