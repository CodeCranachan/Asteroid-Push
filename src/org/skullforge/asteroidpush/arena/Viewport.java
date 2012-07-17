package org.skullforge.asteroidpush.arena;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public interface Viewport {
  void showImage(Image img, Vector2f origin, float rotation, Vector2f size);
}
