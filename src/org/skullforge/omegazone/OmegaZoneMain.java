package org.skullforge.omegazone;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class OmegaZoneMain {

  public static void main(String[] args) {
    try {
      AppGameContainer app = new AppGameContainer(new OmegaZone());
      app.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
 
}
