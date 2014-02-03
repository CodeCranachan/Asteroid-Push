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
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.ui.DesignerUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

public class DesignerGameState extends BasicGameState {

   private StateBasedGame game;
   private BlueprintManipulator manipulator;
   private Scenario scenario;
   private Widget ui;
   private DesignerUiFactory uiFactory;

   public DesignerGameState(DesignerUiFactory uiFactory) {
      this.uiFactory = uiFactory;
   }

   @Override
   public int getID() {
      return StateInfo.DESIGNER.getID();
   }
   
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      this.game = game;
      Player localPlayer = scenario.getLocalPlayer();
      manipulator = new BlueprintManipulator(localPlayer.getShipDesign());
      uiFactory.init(localPlayer, manipulator);
      ui = uiFactory.createUi();
   }

   @Override
   public void keyPressed(int key, char c) {
      switch (key) {
      case Input.KEY_LEFT:
         manipulator.rotateSelectionLeft();
         break;
      case Input.KEY_RIGHT:
         manipulator.rotateSelectionRight();
         break;
      case Input.KEY_SPACE:
         game.enterState(StateInfo.MATCH.getID());
         break;
      case Input.KEY_PERIOD:
         scenario.getLocalPlayer().cycleShipDesign();
         break;
      }
   }

   @Override
   public void mouseMoved(int oldx, int oldy, int newx, int newy) {
      ui.setHover(newx, newy);
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

   public void setScenario(Scenario scenario) {
      this.scenario = scenario;
   }

   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      // TODO Auto-generated method stub
   }

   @Override
   public void mousePressed(int button, int x, int y) {
      ui.mousePressed(button, x, y);
   }
}
