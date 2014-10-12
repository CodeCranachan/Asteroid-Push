package org.codecranachan.asteroidpush.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.visuals.Representation;

public interface Actor {
   Collection<Command> update(int frameNumber);
   Collection<Representation> getRepresentations();
   void destroy();
}