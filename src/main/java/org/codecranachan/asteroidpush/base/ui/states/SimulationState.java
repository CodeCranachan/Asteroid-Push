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

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.base.GameInstance;
import org.codecranachan.asteroidpush.base.ResourceLoader;
import org.codecranachan.asteroidpush.base.ui.StateId;
import org.codecranachan.asteroidpush.base.ui.simulation.SimulationUi;
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
   private SimulationUi ui;

   public SimulationState(ResourceLoader resourceLoader) {
      ui = new SimulationUi(resourceLoader);
      exitSimulation = false;
   }

   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
   }

   @Override
   public void enter(GameContainer container, StateBasedGame game) {
      AsteroidPush push = (AsteroidPush) game;
      ui.setGame(push.getActiveGame());
      exitSimulation = false;
   }

   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      ui.resize(canvas);
      ui.render(graphics);
      
      graphics.setColor(Color.white);
      graphics.drawString("Simulation", 10, 30);
   }

   public void update(GameContainer container,
                      StateBasedGame game,
                      int milliseconds) throws SlickException {
      AsteroidPush push = (AsteroidPush) game;
      GameInstance gameInstance = push.getActiveGame();

      if (gameInstance != null) {
         gameInstance.addRealTime(milliseconds);
      }

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
   }

   @Override
   public void keyReleased(int key, char c) {
   }

}
