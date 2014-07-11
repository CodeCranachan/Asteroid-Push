package org.codecranachan.asteroidpush.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface Actor {
   public Arrow getFocus();

   Collection<Command> update(int frameNumber);
}