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

package org.codecranachan.asteroidpush.visuals.widget;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Label extends BasicWidget {
   private String text;
   private Font font;
   private Color foregroundColor;
   private Color backgroundColor;
   private Color borderColor;

   private static final float FRAME_MARGIN = 2.0f;
   private static final float BORDER_WIDTH = 2.0f;

   public void setText(String text) {
      this.text = text;
   }

   public void setFont(Font font) {
      this.font = font;
   }

   public Font getFont() {
      return font;
   }

   public void setForegroundColor(Color foregroundColor) {
      this.foregroundColor = foregroundColor;
   }

   public void setBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
   }

   public void setBorderColor(Color borderColor) {
      this.borderColor = borderColor;
   }

   public Label() {
      this.text = "empty";
      this.font = null;
      this.foregroundColor = Color.white;
      this.backgroundColor = Color.black;
      this.borderColor = Color.white;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      if (font != null) {
         g.setFont(font);
      }

      g.setLineWidth(BORDER_WIDTH);
      g.setColor(backgroundColor);
      g.fillRoundRect(frame.getX() + FRAME_MARGIN,
                      frame.getY() + FRAME_MARGIN,
                      frame.getWidth() - (BORDER_WIDTH + FRAME_MARGIN),
                      frame.getHeight() - (BORDER_WIDTH + FRAME_MARGIN),
                      5);

      g.setColor(borderColor);
      g.drawRoundRect(frame.getX() + FRAME_MARGIN,
                      frame.getY() + FRAME_MARGIN,
                      frame.getWidth() - (BORDER_WIDTH + FRAME_MARGIN),
                      frame.getHeight() - (BORDER_WIDTH + FRAME_MARGIN),
                      5);

      g.setColor(foregroundColor);
      int textWidth = font.getWidth(text);
      int textHeight = font.getLineHeight();
      g.drawString(text,
                   frame.getCenterX() - (float) textWidth / 2.0f,
                   frame.getCenterY() - (float) textHeight / 2.0f);

      g.setFont(currentFont);
   }

}
