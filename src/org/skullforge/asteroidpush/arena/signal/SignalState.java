package org.skullforge.asteroidpush.arena.signal;

public class SignalState {

   SignalState() {
      forward = 0.0f;
      backward = 0.0f;
      left = 0.0f;
      right = 0.0f;
      clockwise = 0.0f;
      anticlockwise = 0.0f;
   }

   SignalState(SignalState other) {
      copy(other);
   }

   public void copy(SignalState other) {
      this.forward = other.forward;
      this.backward = other.backward;
      this.left = other.left;
      this.right = other.right;
      this.clockwise = other.clockwise;
      this.anticlockwise = other.anticlockwise;
   }

   public float forward;
   public float backward;
   public float left;
   public float right;
   public float clockwise;
   public float anticlockwise;
}
