package org.skullforge.asteroidpush.entities.spaceship;

import org.skullforge.asteroidpush.SignalController;

public interface Effector {
   void update(int frameNumber, SignalController controller);
}
