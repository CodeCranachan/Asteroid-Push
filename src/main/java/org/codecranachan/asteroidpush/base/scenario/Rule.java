package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.simulation.Simulation;

public interface Rule {
   void update(Simulation simulation, int frame);
}
