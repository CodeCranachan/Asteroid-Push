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

package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Label extends BasicWidget {

   public Label(StringBuffer text, Font font) {
      this.text = text;
      this.font = font;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      g.setFont(font);
      g.setColor(Color.green);
      g.drawRoundRect(frame.getX(),
                      frame.getY(),
                      frame.getWidth(),
                      frame.getHeight(),
                      5);
      g.drawString(text.toString(),
                   frame.getCenterX() - (float) font.getWidth(text.toString())
                         / 2.0f,
                   frame.getCenterY() - (float) font.getHeight(text.toString())
                         / 2.0f);
      g.setFont(currentFont);
   }

   private StringBuffer text;
   private Font font;
}
