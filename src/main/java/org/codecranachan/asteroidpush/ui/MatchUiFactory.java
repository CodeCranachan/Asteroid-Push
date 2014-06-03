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

package org.codecranachan.asteroidpush.ui;

import org.codecranachan.asteroidpush.Player;
import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.Simulator;
import org.newdawn.slick.Font;

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
      layoutWidget.setBackground(camera);

      return layoutWidget;
   }

   private ResourceLoader loader;
   private Simulator simulator;
   private Player localPlayer;
}
