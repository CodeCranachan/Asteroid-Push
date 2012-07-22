package org.skullforge.asteroidpush.arena;

public interface SignalTracker {
   float getForwardThrust();
   float getBackwardThrust();
   float getLeftThrust();
   float getRightThrust();
   float getClockwiseThrust();
   float getAnticlockwiseThrust();
}
