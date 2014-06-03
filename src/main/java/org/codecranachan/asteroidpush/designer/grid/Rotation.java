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

package org.codecranachan.asteroidpush.designer.grid;

import org.jbox2d.common.MathUtils;

public class Rotation {

   public static final int FORWARD = 0;
   public static final int LEFT = 1;
   public static final int BACKWARD = 2;
   public static final int RIGHT = 3;

   public Rotation() {
      this.quarterTurns = 0;
   }

   public Rotation(Rotation other) {
      this.quarterTurns = other.quarterTurns;
   }

   public Rotation(Facing facing) {
      switch (facing) {
      case FORWARD:
         this.quarterTurns = 0;
         break;
      case LEFT:
         this.quarterTurns = 1;
         break;
      case BACKWARD:
         this.quarterTurns = 2;
         break;
      case RIGHT:
         this.quarterTurns = 3;
         break;
      }
   }

   public void turnClockwise() {
      quarterTurns += 3;
      normalize();
   }

   public void turnAnticlockwise() {
      quarterTurns += 1;
      normalize();
   }

   public void turnAround() {
      quarterTurns += 2;
      normalize();
   }

   public int getQuarterTurns() {
      return quarterTurns;
   }

   public float getRadians() {
      return quarterTurns * MathUtils.HALF_PI;
   }

   public float getDegrees() {
      return quarterTurns * 90.0f;
   }

   public Facing getFacing() {
      switch (quarterTurns) {
      default:
      case 0:
         return Facing.FORWARD;
      case 1:
         return Facing.LEFT;
      case 2:
         return Facing.BACKWARD;
      case 3:
         return Facing.RIGHT;
      }
   }

   private void normalize() {
      quarterTurns = quarterTurns % 4;
   }

   private int quarterTurns;
}
