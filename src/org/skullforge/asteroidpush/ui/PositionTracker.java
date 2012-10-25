package org.skullforge.asteroidpush.ui;

import org.jbox2d.common.Vec2;

public interface PositionTracker {
   /**
    * Get the center of attention for the object being tracked.
    * 
    * @return A Vec2 to the central point of interest
    */
   Vec2 getCenter();

   /**
    * Get the radius around the tracked object that should be visible.
    * 
    * @return The radius of a circle centered around the main point of interest.
    */
   float getRadius();
}
