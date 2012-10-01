package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Label implements Widget {

   public Label(String text, Font font) {
      this.text = text;
      this.font = font;
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
      Font currentFont = g.getFont();
      g.setFont(font);
      g.setColor(Color.green);
      g.drawRoundRect(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), 5);
      g.drawString(text, frame.getCenterX() - (float) font.getWidth(text)
            / 2.0f, frame.getCenterY() - (float) font.getHeight(text) / 2.0f);
      g.setFont(currentFont);
   }

   private String text;
   private Font font;
}
