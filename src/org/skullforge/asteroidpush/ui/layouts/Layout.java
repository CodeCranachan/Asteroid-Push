package org.skullforge.asteroidpush.ui.layouts;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.skullforge.asteroidpush.ui.Widget;

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

   /**
    * Puts the widget into the named layout element.
    * 
    * @param elementName
    *           name of the layout element to put the widget into.
    * @param widget
    *           the widget to be inserted into the layout.
    */
   public void setWidget(String elementName, Widget widget);

}
