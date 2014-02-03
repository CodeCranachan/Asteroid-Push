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

package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.ui.MatchUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

public class MatchGameState extends BasicGameState {

   public MatchGameState(Simulator simulator, MatchUiFactory uiFactory) {
      this.simulator = simulator;
      this.uiFactory = uiFactory;
      this.timekeeper = new Timekeeper(simulator.getTimeStep());
   }

   public void setScenario(Scenario scenario) {
      this.scenario = scenario;
   }

   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      uiFactory.init(simulator, scenario.getLocalPlayer());
      ui = uiFactory.createUi();
      this.game = game;
   }

   @Override
   public void enter(GameContainer container, StateBasedGame game) {
      simulator.clear();
      simulator.initialize(scenario);
      timekeeper = new Timekeeper(simulator.getTimeStep());
   }

   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      if (ui != null) {
         ui.resize(canvas);
         ui.render(graphics);
      }
   }

   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      timekeeper.addRealTime(delta);
      simulator.stepToFrame(timekeeper.getGameTime());
   }

   @Override
   public int getID() {
      return StateInfo.MATCH.getID();
   }

   @Override
   public void keyPressed(int key, char c) {
      if (Input.KEY_ESCAPE == key) {
         game.enterState(StateInfo.DESIGNER.getID());
      } else {
         scenario.getLocalPlayer().handleKeyDown(key);
      }
   }

   @Override
   public void keyReleased(int key, char c) {
      scenario.getLocalPlayer().handleKeyUp(key);
   }

   private Simulator simulator;
   private Timekeeper timekeeper;
   private Scenario scenario;
   private StateBasedGame game;
   private MatchUiFactory uiFactory;
   private Widget ui;
}
