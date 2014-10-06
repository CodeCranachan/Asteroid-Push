package org.codecranachan.asteroidpush.simulation.modular;

public class Plug {
   private Behavior behavior;
   private int index;

   public Plug(Behavior behavior, int index) {
      this.behavior = behavior;
      this.index = index;
   }

   public Behavior getBehavior() {
      return behavior;
   }

   public int getIndex() {
      return index;
   }
}
