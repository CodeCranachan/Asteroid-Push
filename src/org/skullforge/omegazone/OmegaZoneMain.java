package org.skullforge.omegazone;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class OmegaZoneMain {

  public static void main(String[] args) throws SlickException{
    AppGameContainer app = new AppGameContainer(new OmegaZone(new BasicStateFactory()));
    app.start();
  }
 
}
