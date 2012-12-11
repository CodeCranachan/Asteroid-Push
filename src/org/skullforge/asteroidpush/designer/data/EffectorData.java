package org.skullforge.asteroidpush.designer.data;

import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.entities.spaceship.Effector;

public interface EffectorData {
   Effector createEffector(Transform transform, Body body);
}
