package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.ui.layouts.Layout;

public class IngameUiFactory implements UiFactory {

   public IngameUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   @Override
   public void createUi(Layout layout) {
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);
      Widget playerLabel = new Label("PlayerName", font);
      layout.setWidget("info", playerLabel);
   }

   private ResourceLoader loader;
}
