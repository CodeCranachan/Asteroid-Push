package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;

public interface Effector {
   Collection<SimulatorCommand> update(int frameNumber, SignalController controller);
}
