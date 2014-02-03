//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
