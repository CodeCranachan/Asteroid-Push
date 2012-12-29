package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class BasicWidget implements Widget {

   private Vector2f currentHover;
   private Rectangle frame;

   @Override
   public void resize(Rectangle frame) {
      this.frame = frame;
   }

   @Override
   public void render(Graphics g) {
      if(getFrame() == null) {
         return;
      }
      
      g.setLineWidth(3.0f);
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

   @Override
   public void setHover(float x, float y) {
      this.currentHover = new Vector2f(x, y);
   }

   @Override
   public void resetHover() {
      this.currentHover = null;
   }

}
