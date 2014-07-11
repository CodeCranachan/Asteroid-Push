package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;

public interface Behavior {
   public Collection<BodyVertex> getNodes();

   public Collection<Command> onAttach(RigidBody body);

   public Collection<Command> onDetach(RigidBody body);

   public Collection<Command> onReassign(RigidBody srcBody, RigidBody dstBody);

   public Collection<Command> update(Map<BodyVertex, RigidBody> body, int frame);
}
