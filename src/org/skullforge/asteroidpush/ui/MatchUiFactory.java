package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ResourceLoader;
import org.skullforge.asteroidpush.Simulator;

public class MatchUiFactory {

   public MatchUiFactory(ResourceLoader loader) {
      this.loader = loader;
   }

   public void init(Simulator sim, Player localPlayer) {
      this.simulator = sim;
      this.localPlayer = localPlayer;
   }

   public Widget createUi() {
      SimpleLayout layoutWidget = new SimpleLayout();
      Font font = loader.loadFont("Alfphabet-IV.ttf", 14);
      Widget playerLabel = new Label(new StringBuffer(localPlayer.getName()), font);
      layoutWidget.setWidget("info", playerLabel);
      Widget controlLabel = new Label(localPlayer.getControls(), font);
      layoutWidget.setWidget("control", controlLabel);

      TrackingCamera camera = new TrackingCamera(simulator);
      camera.setPositionTracker(localPlayer);
      layoutWidget.setWidget("background", camera);

      return layoutWidget;
   }

   private ResourceLoader loader;
   private Simulator simulator;
   private Player localPlayer;
}
