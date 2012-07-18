package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class StaticViewport implements Viewport {
  public StaticViewport() {
  }

  public void showImage(Image img,
                        Vector2f origin,
                        float rotation,
                        Vector2f size) {
    img.setCenterOfRotation(size.getX()/2.0f, size.getY()/2.0f);
    img.setRotation(rotation);
    graphicsPort.drawImage(img,
                           origin.getX(),
                           origin.getY(),
                           origin.getX() + size.getX(),
                           origin.getY() + size.getY(),
                           0.0f,
                           0.0f,
                           img.getWidth(),
                           img.getHeight());
  }

  public void setGraphics(Graphics g) {
    graphicsPort = g;
    g.scale(25.0f, 25.0f);
  }
  
  private Graphics graphicsPort;
}
