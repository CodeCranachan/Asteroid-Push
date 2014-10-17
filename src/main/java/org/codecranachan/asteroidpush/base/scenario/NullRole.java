package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.ui.simulation.Viewport;

public class NullRole implements Role {
   private Player player;

   public NullRole(Player player) {
      this.player = player;
   }

   public Player getPlayer() {
      return player;
   }

   public Viewport getInterface() {
      // TODO implement a placeholder viewport
      return null;
   }

}
