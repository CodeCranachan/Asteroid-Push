package org.skullforge.asteroidpush.designer.data;

import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.doodads.spaceship.Effector;

public interface EffectorFactory {
   Effector create(Transform transform, Body body);
}
