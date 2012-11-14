package org.skullforge.asteroidpush.designer.grid;

public enum Rotation {
   BOW, STARBOARD, PORT, STERN;

   public Rotation turnClockwise() {
      switch (this) {
      case BOW:
         return STARBOARD;
      case STARBOARD:
         return STERN;
      case STERN:
         return PORT;
      case PORT:
      default:
         return BOW;
      }
   }

   public Rotation turnAnticlockwise() {
      switch (this) {
      case BOW:
         return PORT;
      case PORT:
         return STERN;
      case STERN:
         return PORT;
      case STARBOARD:
      default:
         return BOW;
      }
   }

   public Rotation turnAround() {
      switch (this) {
      case BOW:
         return STERN;
      case PORT:
         return STARBOARD;
      case STERN:
         return STERN;
      case STARBOARD:
      default:
         return PORT;
      }
   }
}
