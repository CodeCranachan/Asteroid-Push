package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

public class Label implements Widget {

   public Label(float x, float y, String text, UnicodeFont font) {
      this.x = x;
      this.y = y;
      this.text = text;
      this.font = font;
   }

   @Override
   public void render(Graphics g) {
      g.scale(0.08f, 0.08f);
      Font currentFont = g.getFont();
      g.setFont(font);
      g.drawString(text, x, y);
      g.setFont(currentFont);
      g.resetTransform();
   }

   @Override
   public Rectangle getBoundingBox() {
      Rectangle boundingBox = new Rectangle(x, y, 0, 0);
      boundingBox.setSize(font.getWidth(text), font.getHeight(text));
      return boundingBox;
   }

   private float x, y;
   private String text;
   private UnicodeFont font;
}
