package org.skullforge.asteroidpush.arena.signal;

import org.newdawn.slick.Input;
import org.skullforge.asteroidpush.arena.SignalTracker;

public class KeySignalTracker implements SignalTracker {

   public KeySignalTracker() {
      sampledSignals = new SignalState();
      currentSignals = new SignalState();
   }

   @Override
   public float getForwardThrust() {
      return sampledSignals.forward;
   }

   @Override
   public float getBackwardThrust() {
      return sampledSignals.backward;
   }

   @Override
   public float getLeftThrust() {
      return sampledSignals.left;
   }

   @Override
   public float getRightThrust() {
      return sampledSignals.right;
   }

   @Override
   public float getClockwiseThrust() {
      return sampledSignals.clockwise;
   }

   @Override
   public float getAnticlockwiseThrust() {
      return sampledSignals.anticlockwise;
   }

   public void keyPressed(int key) {
      switch (key) {
      case Input.KEY_A:
         currentSignals.anticlockwise = 1.0f;
         break;
      case Input.KEY_S:
         currentSignals.backward = 1.0f;
         break;
      case Input.KEY_D:
         currentSignals.clockwise = 1.0f;
         break;
      case Input.KEY_Q:
         currentSignals.left = 1.0f;
         break;
      case Input.KEY_W:
         currentSignals.forward = 1.0f;
         break;
      case Input.KEY_E:
         currentSignals.right = 1.0f;
         break;
      default:
         break;
      }
   }

   public void keyReleased(int key) {
      switch (key) {
      case Input.KEY_A:
         currentSignals.anticlockwise = 0.0f;
         break;
      case Input.KEY_S:
         currentSignals.backward = 0.0f;
         break;
      case Input.KEY_D:
         currentSignals.clockwise = 0.0f;
         break;
      case Input.KEY_Q:
         currentSignals.left = 0.0f;
         break;
      case Input.KEY_W:
         currentSignals.forward = 0.0f;
         break;
      case Input.KEY_E:
         currentSignals.right = 0.0f;
         break;
      default:
         break;
      }
   }

   public void takeSnapshot() {
      sampledSignals.copy(currentSignals);
   }

   private SignalState currentSignals;
   private SignalState sampledSignals;
}