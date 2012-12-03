package org.skullforge.asteroidpush.designer.modules.data;

import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.doodads.Effector;

public interface EffectorFactory {
   Effector create(Transform transform, Body body);
}
