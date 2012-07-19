package org.skullforge.asteroidpush.arena;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public interface Viewport {
  void setGraphics(Graphics g);
  void showImage(Image img, Vec2 origin, float rotation, Vec2 size);
}
