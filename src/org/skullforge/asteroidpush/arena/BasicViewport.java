package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class BasicViewport implements Viewport {
  Graphics graphicsPort;
  
  public BasicViewport(Graphics g) {
    graphicsPort = g;
  }
  
  @Override
  public void showImage(Image img, Rectangle rect) {
    graphicsPort.drawImage(img, rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), 0.0f, 0.0f, img.getWidth(), img.getHeight());
  }
}
