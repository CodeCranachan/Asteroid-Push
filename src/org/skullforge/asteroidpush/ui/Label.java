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
   public void render(Graphics g, Rectangle frame) {
      Font currentFont = g.getFont();
      g.setFont(font);
      g.setColor(Color.green);
      g.drawRoundRect(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight(), 5);
      g.drawString(text.toString(), frame.getCenterX() - (float) font.getWidth(text.toString())
            / 2.0f, frame.getCenterY() - (float) font.getHeight(text.toString()) / 2.0f);
      g.setFont(currentFont);
   }

   private StringBuffer text;
   private Font font;
}
