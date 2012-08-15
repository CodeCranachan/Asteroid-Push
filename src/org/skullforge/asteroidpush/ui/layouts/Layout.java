package org.skullforge.asteroidpush.ui.layouts;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Layout {
   /**
    * Draws the user interface as described in this layout on the canvas.
    * 
    * @param container
    *           the game container context to be drawn.
    * @param graphics
    *           the graphics adapter to use.
    */
   public void render(GameContainer container, Graphics graphics);
}
