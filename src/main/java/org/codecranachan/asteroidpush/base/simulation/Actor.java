package org.codecranachan.asteroidpush.base.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface Actor {
   Collection<Command> update(int frameNumber);
   Collection<Representation> getRepresentations();
   Arrow getFocus();
   void destroy();
}