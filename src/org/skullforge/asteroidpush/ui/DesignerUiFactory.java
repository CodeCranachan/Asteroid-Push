package org.skullforge.asteroidpush.ui;

import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.designer.ShipDesign;

public class DesignerUiFactory {

   public DesignerUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public void init(ShipDesign design) {
      this.shipDesign = design;
   }

   public Widget createUi() {
      Widget ui = new ShipDesignView(shipDesign, loader.loadFont("Alfphabet-IV.ttf", 10));      
      return ui;
   }

   private ResourceLoader loader;
   private ShipDesign shipDesign;
}
