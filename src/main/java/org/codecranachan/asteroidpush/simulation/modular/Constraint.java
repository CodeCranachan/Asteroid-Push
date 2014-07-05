package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;

import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.simulation.RigidBody;

public interface Constraint {
   Collection<SimulatorCommand> update (RigidBody bodyA, RigidBody bodyB, int framenumber);
   BodyVertex getNodeA();
   BodyVertex getNodeB();
}
