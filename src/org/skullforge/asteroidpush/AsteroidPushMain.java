package org.skullforge.asteroidpush;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class AsteroidPushMain {

   public static void main(String[] args) throws SlickException{
     
    GameStateFactory stateFactory = new GameStateFactory();
    ResourceLoader resourceLoader = new ResourceLoader();
    AsteroidPush game = new AsteroidPush(stateFactory, resourceLoader);
    AppGameContainer app = new AppGameContainer(game);
    app.start();
  }
}
