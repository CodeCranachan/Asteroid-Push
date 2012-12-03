package org.skullforge.asteroidpush.doodads;

import org.skullforge.asteroidpush.SignalController;

public interface Effector {
   void update(int frameNumber, SignalController controller);
}
