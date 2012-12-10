package org.skullforge.asteroidpush.doodads.spaceship;

import org.skullforge.asteroidpush.SignalController;

public interface Effector {
   void update(int frameNumber, SignalController controller);
}
