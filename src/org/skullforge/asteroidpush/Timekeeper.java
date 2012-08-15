package org.skullforge.asteroidpush;

public class Timekeeper {
   public Timekeeper(float frameTime) {
      timeAccumulator = 0;
      this.frameTime = frameTime;
   }

   public void addRealTime(int milliseconds) {
      timeAccumulator += (float) milliseconds / 1000.0f;
   }

   public int getGameTime() {
      return (int) Math.floor(timeAccumulator / frameTime);
   }

   float timeAccumulator;
   float frameTime;
}
