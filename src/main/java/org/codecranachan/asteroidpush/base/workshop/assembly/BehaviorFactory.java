package org.codecranachan.asteroidpush.base.workshop.assembly;

import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.utils.Arrow;

public interface BehaviorFactory extends Representable {
   /**
    * Create a new behavior at the given location.
    * 
    * @param offset
    *           the translation and rotation of the behavior relative to the
    *           body it is attached to. For a ModularActor, this is the
    *           placement of the Part that contains the BehaviorFactory. This
    *           does not include the relative placement of the behavior within
    *           that part (this should be passed as a parameter to the factory
    *           and combined with the offset given in this method)
    * @return a new behavior ready to be attached to a body
    */
   public Behavior createBehavior(Arrow offset);
}
