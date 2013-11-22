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
