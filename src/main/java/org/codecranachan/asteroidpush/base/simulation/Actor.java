package org.codecranachan.asteroidpush.base.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public interface Actor {
   Collection<Command> update(int frameNumber);
   Collection<Representation> getRepresentations();
   void destroy();
}