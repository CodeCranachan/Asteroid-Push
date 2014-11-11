package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.input.Controller;

public class Player {
   Controller controller;

   public Player(Controller controller) {
      this.controller = controller;
   }

   public Controller getController() {
      return controller;
   }

}
