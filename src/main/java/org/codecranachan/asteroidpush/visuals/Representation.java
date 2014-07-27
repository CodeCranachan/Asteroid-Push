package org.codecranachan.asteroidpush.visuals;

import org.newdawn.slick.Graphics;

public interface Representation {
   public void render(Graphics g);
   public int getPriority();
}
