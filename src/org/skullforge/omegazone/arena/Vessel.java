package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Vessel implements Entity {

  public Vessel(Image image) throws SlickException {
    vesselImage = image;
    rotation = 0;
  }
  
  @Override
  public void render(Graphics g) {
    vesselImage.setRotation(rotation);
    g.drawImage(vesselImage, 0.0f, 0.0f);
  }

  @Override
  public void update(int delta) {
    rotation += (float)delta / 100.0f;
  }

  private Image vesselImage;
  private float rotation;
}
