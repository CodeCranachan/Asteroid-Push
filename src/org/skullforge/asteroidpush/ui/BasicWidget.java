package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class BasicWidget implements Widget {

   private Vector2f currentHover;

   @Override
   public void render(Graphics g, Rectangle frame) {
   }

   public Vector2f getHover() {
      return this.currentHover;
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
