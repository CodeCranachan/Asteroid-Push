package org.codecranachan.asteroidpush.base.ui;

import org.codecranachan.asteroidpush.AsteroidPush;

public interface StateContext {
   void enterContext(AsteroidPush game);
   
   void exitContext(AsteroidPush game); 
}
