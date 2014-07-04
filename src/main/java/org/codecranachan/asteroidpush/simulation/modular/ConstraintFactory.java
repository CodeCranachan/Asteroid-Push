package org.codecranachan.asteroidpush.simulation.modular;

public interface ConstraintFactory {
   public Constraint createConstraint(BodyVertex nodeA, BodyVertex nodeB);
}
