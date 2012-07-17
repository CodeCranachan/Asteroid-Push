package org.skullforge.asteroidpush.arena.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.SlickException;
import org.skullforge.asteroidpush.arena.Entity;
import org.skullforge.asteroidpush.arena.Viewport;

public class Vessel implements Entity {

  public Vessel(Image image) throws SlickException {
    vesselImage = image;
    rotation = 0.0f;
    origin = new Vector2f(2.0f, 2.0f);
    size = new Vector2f(2.0f, 2.0f);
  }
  
  @Override
  public void render(Viewport view) {
    view.showImage(vesselImage, origin, rotation, size);
  }

  @Override
  public void update(int delta) {
    rotation += (float)delta / 25.0f;
  }

  private Image vesselImage;
  private float rotation;
  private Vector2f origin;
  private Vector2f size;
}
