package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.MathUtils;

public class Trigonometry {
   static public float radToDeg(float rad) {
      return rad * 360.0f / MathUtils.TWOPI;
   }

   static public float degToRad(float angle) {
      return angle * MathUtils.TWOPI / 360.0f;
   }
}
