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

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.junit.Test;

public class FacingTest {

   @Test
   public void testFromTransform() {
      // Check zero
      checkTransformMapping(0.0f, Facing.FORWARD);

      // Check positive arc
      checkTransformMapping(MathUtils.HALF_PI, Facing.LEFT);
      checkTransformMapping(MathUtils.PI, Facing.BACKWARD);
      checkTransformMapping(MathUtils.THREE_HALVES_PI, Facing.RIGHT);
      checkTransformMapping(MathUtils.TWOPI, Facing.FORWARD);

      // Check negative arc
      checkTransformMapping(-MathUtils.TWOPI, Facing.FORWARD);
      checkTransformMapping(-MathUtils.THREE_HALVES_PI, Facing.LEFT);
      checkTransformMapping(-MathUtils.PI, Facing.BACKWARD);
      checkTransformMapping(-MathUtils.HALF_PI, Facing.RIGHT);
   }

   private void checkTransformMapping(float angle, Facing expectedFacing) {
      Transform testTransform = new Transform();
      testTransform.set(new Vec2(), angle);
      assertThat("Checking angle " + angle / MathUtils.PI + "\u03C0",
                 Facing.fromTransform(testTransform),
                 equalTo(expectedFacing));
   }
}
