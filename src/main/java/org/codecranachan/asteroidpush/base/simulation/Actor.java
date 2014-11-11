package org.codecranachan.asteroidpush.base.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.input.Controllable;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.FieldOfView;

public interface Actor extends Representable, Controllable {
   Collection<Command> update(int frameNumber);
   Collection<Representation> getRepresentations();
   FieldOfView getFieldOfView();
   void destroy();
}