package org.skullforge.asteroidpush;

public enum StateInfo {
   INVALID(0), ARENA(1), MATCH(2);

   private int stateID;

   StateInfo(int number) {
      stateID = number;
   }

   public int getID() {
      return stateID;
   }
}
