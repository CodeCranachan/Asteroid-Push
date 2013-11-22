package org.skullforge.asteroidpush.designer.grid;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;

public enum Facing {
   FORWARD, LEFT, RIGHT, BACKWARD;

   public static Facing fromTransform(Transform transform) {
      float angle = transform.q.getAngle();
      Facing result;
      if (angle < -3 * MathUtils.QUARTER_PI) {
         result = BACKWARD;
      } else if (angle < -MathUtils.QUARTER_PI) {
         result = RIGHT;
      } else if (angle < MathUtils.QUARTER_PI) {
         result = FORWARD;
      } else if (angle < 3 * MathUtils.QUARTER_PI) {
         result = LEFT;
      } else {
         result = BACKWARD;
      }
      return result;
   }
}
