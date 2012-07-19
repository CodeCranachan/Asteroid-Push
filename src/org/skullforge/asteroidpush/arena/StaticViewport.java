package org.skullforge.asteroidpush.arena;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class StaticViewport implements Viewport {
  public StaticViewport() {
  }

  public void showImage(Image img,
                        Vec2 origin,
                        float rotation,
                        Vec2 size) {
    img.setCenterOfRotation(size.x/2.0f, size.y/2.0f);
    img.setRotation(rotation);
    graphicsPort.drawImage(img,
                           origin.x,
                           origin.y,
                           origin.x + size.y,
                           origin.y + size.y,
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
