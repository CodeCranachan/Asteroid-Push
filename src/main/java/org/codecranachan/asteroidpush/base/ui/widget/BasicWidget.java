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

package org.codecranachan.asteroidpush.base.ui.widget;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BasicWidget implements Widget {

   private Vector2f currentHover;
   private Rectangle frame;

   private static float LINE_WIDTH = 3.0f;

   public void resize(Rectangle frame) {
      this.frame = frame;
   }

   public void render(Graphics g) {
      if (getFrame() == null) {
         return;
      }

      g.setAntiAlias(true);
      g.setLineWidth(LINE_WIDTH);
      g.setColor(Color.darkGray);
      g.fill(getFrame());
      g.setColor(Color.orange);
      g.draw(getFrame());
   }

   public Vector2f getHover() {
      return this.currentHover;
   }

   public Rectangle getFrame() {
      return this.frame;
   }

   public void setFrame(Rectangle frame) {
      this.frame = frame;
   }

   public void setHover(float x, float y) {
      this.currentHover = new Vector2f(x, y);
   }

   public void resetHover() {
      this.currentHover = null;
   }

   public void mousePressed(int button, int x, int y) {
   }

   public void update(GameContainer container, StateBasedGame game, int delta) {
   }

   public void keyPressed(int key, char c) {
   }
   
   public void keyReleased(int key, char c) {
   }
}
