package org.codecranachan.asteroidpush.base.workshop.assembly;

public class Plug {
   private BehaviorFactory factory;
   private int index;

   public Plug(BehaviorFactory factory, int index) {
      this.factory = factory;
      this.index = index;
   }

   public BehaviorFactory getFactory() {
      return factory;
   }

   public int getIndex() {
      return index;
   }
}
