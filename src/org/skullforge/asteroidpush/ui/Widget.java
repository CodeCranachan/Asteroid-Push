package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public interface Widget {
   void resize(Rectangle frame);
   void render(Graphics g);
   void setHover(float x, float y);
   void resetHover();
   void mousePressed(int button, int x, int y);
}
