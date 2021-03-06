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
import org.codecranachan.asteroidpush.base.ResourceLoader;
import org.codecranachan.asteroidpush.base.Settings;
import org.codecranachan.asteroidpush.base.ui.StateId;
import org.codecranachan.asteroidpush.base.ui.workshop.WorkshopUi;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WorkshopState extends BasicGameState {
   private ResourceLoader loader;
   private WorkshopUi ui;

   public WorkshopState(ResourceLoader resourceLoader) {
      loader = resourceLoader;
      ui = null;
   }

   public int getID() {
      return StateId.WORKSHOP;
   }

   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      Settings settings = pullSettings(game);
      ui = new WorkshopUi(settings.getBlueprints(), loader);
   }

   private Settings pullSettings(StateBasedGame game) {
      AsteroidPush app = (AsteroidPush) game;
      Settings settings = app.getSettings();
      return settings;
   }

   public void keyPressed(int key, char c) {
      switch (key) {
      case Input.KEY_ESCAPE:
         break;
      default:
         ui.keyPressed(key);
         break;
      }
   }

   public void mouseMoved(int oldx, int oldy, int newx, int newy) {
      ui.setHover(newx, newy);
   }

   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      ui.resize(canvas);
      ui.render(graphics);
      
      graphics.setColor(Color.white);
      graphics.drawString("Workshop", 10, 30);
   }

   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      ui.update(container, game, delta);
   }

   public void mousePressed(int button, int x, int y) {
      ui.mousePressed(button, x, y);
   }

}