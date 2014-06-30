package org.codecranachan.asteroidpush.workshop.assembly;

import org.codecranachan.asteroidpush.simulation.Constraint;
import org.codecranachan.asteroidpush.workshop.spaceship.BodyVertex;

public interface ConstraintFactory {
   public Constraint createConstraint(BodyVertex nodeA, BodyVertex nodeB);
}
