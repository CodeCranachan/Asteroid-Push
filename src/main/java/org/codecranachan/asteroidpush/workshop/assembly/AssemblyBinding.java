package org.codecranachan.asteroidpush.workshop.assembly;

import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;

class AssemblyBinding {
   private int index;
   private BehaviorFactory factory;

   public AssemblyBinding(BehaviorFactory factory, int index) {
      this.factory = factory;
      this.index = index;
   }

   public int getIndex() {
      return index;
   }

   public BehaviorFactory getFactory() {
      return factory;
   }
}