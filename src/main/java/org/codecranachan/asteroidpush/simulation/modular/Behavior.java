package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.Map;

import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.simulation.RigidBody;

public interface Behavior {
   public Collection<BodyVertex> getNodes();

   public Collection<SimulatorCommand> onAttach(RigidBody body);

   public Collection<SimulatorCommand> onDetach(RigidBody body);

   public Collection<SimulatorCommand> onReassign(RigidBody srcBody,
                                                  RigidBody dstBody);

   public Collection<SimulatorCommand> update(Map<BodyVertex, RigidBody> body, int frame);
}
