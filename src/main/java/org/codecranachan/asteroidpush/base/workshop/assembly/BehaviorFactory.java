package org.codecranachan.asteroidpush.base.workshop.assembly;

import java.util.List;

import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface BehaviorFactory extends Representable {
   public List<Socket> getSockets();

   public Behavior createBehavior(Arrow offset);
}
