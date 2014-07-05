package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;

import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.simulation.RigidBody;

public interface Behavior {
   public BodyVertex getNode();

   public Collection<SimulatorCommand> onAttach(RigidBody body);

   public Collection<SimulatorCommand> onDetach(RigidBody body);

   public Collection<SimulatorCommand> onReassign(RigidBody srcBody,
                                                  RigidBody dstBody);

   public Collection<SimulatorCommand> update(RigidBody body, int frame);
}
