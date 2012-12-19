package org.skullforge.asteroidpush.designer.grid;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;

public enum Facing {
   FORWARD, LEFT, RIGHT, BACKWARD;

   public static Facing fromTransform(Transform transform) {
      float angle = transform.getAngle();

      if (angle < MathUtils.QUARTER_PI) {
         return FORWARD;
      } else if (angle < MathUtils.HALF_PI + MathUtils.QUARTER_PI) {
         return LEFT;
      } else if (angle < MathUtils.PI + MathUtils.QUARTER_PI) {
         return BACKWARD;
      } else if (angle < MathUtils.THREE_HALVES_PI + MathUtils.QUARTER_PI) {
         return RIGHT;
      } else {
         return FORWARD;
      }
   }
}
