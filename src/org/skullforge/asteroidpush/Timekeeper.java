package org.skullforge.asteroidpush;

public class Timekeeper {
   public Timekeeper(int timeRatio) {
      timeAccumulator = 0;
      this.timeRatio = timeRatio;
   }

   public void addRealTime(int milliseconds) {
      timeAccumulator += milliseconds;
   }
   
   public int getGameTime() {
      return timeAccumulator / timeRatio;
   }
   
   int timeAccumulator;
   int timeRatio;
}
