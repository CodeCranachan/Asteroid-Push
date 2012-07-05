package org.skullforge.asteroidpush;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class AsteroidPushMain {

  public static void main(String[] args) throws SlickException{
    AppGameContainer app = new AppGameContainer(new AsteroidPush(new BasicStateFactory()));
    app.start();
  }
 
}
