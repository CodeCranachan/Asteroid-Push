package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;

public interface ImpactListener {
   void handleImpact(Vec2 WorldPoint, float magnitude);
}
