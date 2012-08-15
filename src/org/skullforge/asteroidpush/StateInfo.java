package org.skullforge.asteroidpush;

public enum StateInfo {
   INVALID(0), MATCH(1);

   private int stateID;

   StateInfo(int number) {
      stateID = number;
   }

   public int getID() {
      return stateID;
   }
}
