package org.skullforge.asteroidpush.designer.grid;

import org.jbox2d.common.MathUtils;

public class Rotation {

   public static final int FORWARD = 0;
   public static final int LEFT = 1;
   public static final int BACKWARD = 2;
   public static final int RIGHT = 3;

   public Rotation() {
      this.quarterTurns = 0;
   }

   public Rotation(Rotation other) {
      this.quarterTurns = other.quarterTurns;
   }
   
   public Rotation(Facing facing) {
      switch (facing) {
      case FORWARD:
         this.quarterTurns = 0;
         break;
      case LEFT:
         this.quarterTurns = 1;
         break;
      case BACKWARD:
         this.quarterTurns = 2;
         break;
      case RIGHT:
         this.quarterTurns = 3;
         break;
      }
   }
   
   public void turnClockwise() {
      quarterTurns += 3;
      normalize();
   }

   public void turnAnticlockwise() {
      quarterTurns += 1;
      normalize();
   }

   public void turnAround() {
      quarterTurns += 2;
      normalize();
   }
   
   public int getQuarterTurns() {
      return quarterTurns;
   }

   public float getRadians() {
      return quarterTurns * MathUtils.HALF_PI;
   }

   public float getDegrees() {
      return quarterTurns * 90.0f;
   }

   private void normalize() {
      quarterTurns = quarterTurns % 4;
   }

   private int quarterTurns;
}
