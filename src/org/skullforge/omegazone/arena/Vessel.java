package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Vessel implements Entity {

  public Vessel(Image image) throws SlickException {
    vesselImage = image;
  }
  
  @Override
  public void render(Graphics g) {
    g.drawImage(vesselImage, 0.0f, 0.0f);
  }

  @Override
  public void update(int delta) {
    // TODO Auto-generated method stub

  }

  private Image vesselImage;
}
