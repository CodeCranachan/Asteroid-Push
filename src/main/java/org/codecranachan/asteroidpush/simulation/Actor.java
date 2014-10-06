package org.codecranachan.asteroidpush.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.simulation.command.Command;

public interface Actor {
   Collection<Command> update(int frameNumber);
   void destroy();
}