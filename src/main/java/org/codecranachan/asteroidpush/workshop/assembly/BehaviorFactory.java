package org.codecranachan.asteroidpush.workshop.assembly;

import org.codecranachan.asteroidpush.simulation.Behavior;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface BehaviorFactory {
   public Behavior createBehavior(Arrow placement);
}
