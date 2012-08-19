package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.Simulator;

public class IngameUiFactory implements UiFactory {

   public IngameUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   @Override
   public Widget createUi(Simulator sim) {
      SimpleLayout layoutWidget = new SimpleLayout();
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);
      Widget playerLabel = new Label("PlayerName", font);
      layoutWidget.setWidget("info", playerLabel);
      
      
      StaticCamera camera = new StaticCamera(sim);
      layoutWidget.setWidget("background", camera);
      
      return layoutWidget;
   }

   private ResourceLoader loader;
}
