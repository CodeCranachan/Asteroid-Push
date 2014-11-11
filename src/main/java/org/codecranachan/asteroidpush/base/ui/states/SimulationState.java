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

package org.codecranachan.asteroidpush.base.ui.states;

import java.util.Collection;
import java.util.Vector;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.base.GameInstance;
import org.codecranachan.asteroidpush.base.ResourceLoader;
import org.codecranachan.asteroidpush.base.scenario.Player;
import org.codecranachan.asteroidpush.base.ui.StateId;
import org.codecranachan.asteroidpush.base.ui.widget.Widget;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SimulationState extends BasicGameState {

   private boolean exitSimulation;
   private Collection<Widget> controlConsumers;

   public SimulationState(ResourceLoader resourceLoader) {
      exitSimulation = false;
      controlConsumers = new Vector<Widget>();
   }

   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
   }

   @Override
   public void enter(GameContainer container, StateBasedGame game) {
      exitSimulation = false;
   }

   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      AsteroidPush push = (AsteroidPush) game;
      GameInstance activeGame = push.getActiveGame();
      if (activeGame == null) {
         return;
      }
      Player localPlayer = push.getLocalPlayer();
      Widget localUi = activeGame.getUi(localPlayer);

      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      localUi.resize(canvas);
      localUi.render(graphics);

      graphics.setColor(Color.white);
      graphics.drawString("Simulation", 10, 30);
   }

   public void update(GameContainer container,
                      StateBasedGame game,
                      int milliseconds) throws SlickException {
      AsteroidPush push = (AsteroidPush) game;
      GameInstance activeGame = push.getActiveGame();
      Player localPlayer = push.getLocalPlayer();
      Widget localUi = activeGame.getUi(localPlayer);

      assert activeGame != null;
      assert localPlayer != null;
      assert localUi != null;

      activeGame.addRealTime(milliseconds);
      localUi.update(container, game, milliseconds);

      controlConsumers.clear();
      controlConsumers.add(localUi);

      if (exitSimulation) {
         push.popContext();
      }
   }

   @Override
   public int getID() {
      return StateId.SIMULATION;
   }

   @Override
   public void keyPressed(int key, char c) {
      if (Input.KEY_ESCAPE == key) {
         exitSimulation = true;
      }

      for (Widget consumer : controlConsumers) {
         consumer.keyPressed(key, c);
      }
   }

   @Override
   public void keyReleased(int key, char c) {
      for (Widget consumer : controlConsumers) {
         consumer.keyReleased(key, c);
      }
   }

}
