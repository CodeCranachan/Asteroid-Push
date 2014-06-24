package org.codecranachan.asteroidpush.workshop.assembly;

import org.codecranachan.asteroidpush.simulation.Constraint;
import org.codecranachan.asteroidpush.simulation.RigidBody;

public interface ConstraintFactory {
   public Constraint createConstraint(RigidBody bodyA, RigidBody bodyB);
}
