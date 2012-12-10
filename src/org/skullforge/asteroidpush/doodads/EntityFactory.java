package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;

public interface EntityFactory {
   Entity createEntity(Vec2 location);
}
