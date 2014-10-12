package org.codecranachan.asteroidpush.simulation.actors.modular;

import java.util.List;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.visuals.Representable;
import org.codecranachan.asteroidpush.workshop.assembly.Socket;

public interface BehaviorFactory extends Representable {
   public List<Socket> getSockets();

   public Behavior createBehavior(Arrow offset);
}
