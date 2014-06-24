package org.codecranachan.asteroidpush.workshop.spaceship;

import org.codecranachan.asteroidpush.workshop.assembly.ConstraintFactory;

public class Softlink {
   private Hardpoint hardpointA;
   private Hardpoint hardpointB;
   private ConstraintFactory constraintFactory;

   public Softlink(Hardpoint hardpointA, Hardpoint hardpointB,
         ConstraintFactory constraintFactory) {
      this.hardpointA = hardpointA;
      this.hardpointB = hardpointB;
      this.constraintFactory = constraintFactory;
   }

   public Hardpoint getHardpointA() {
      return hardpointA;
   }

   public Hardpoint getHardpointB() {
      return hardpointB;
   }

   public ConstraintFactory getConstraintFactory() {
      return constraintFactory;
   }
}