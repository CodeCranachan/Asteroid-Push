package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class BasicViewport implements Viewport {
  Graphics graphicsPort;

  public BasicViewport(Graphics g) {
    graphicsPort = g;
    graphicsPort.scale(25.0f, 25.0f);
  }

  @Override
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
}
