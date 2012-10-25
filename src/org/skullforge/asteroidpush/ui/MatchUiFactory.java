package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.Simulator;

public class MatchUiFactory {

   public MatchUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public void init(Simulator sim) {
      this.simulator = sim;
   }

   public Widget createUi() {
      SimpleLayout layoutWidget = new SimpleLayout();
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);
      Widget playerLabel = new Label("PlayerName", font);
      layoutWidget.setWidget("info", playerLabel);

      Widget camera = new TrackingCamera(simulator);
      layoutWidget.setWidget("background", camera);

      return layoutWidget;
   }

   private ResourceLoader loader;
   private Simulator simulator;
}
