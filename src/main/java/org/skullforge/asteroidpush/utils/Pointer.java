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

package org.skullforge.asteroidpush.utils;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Pointer {
   private float angle;
   private Vec2 position;

   public Pointer() {
      this.angle = 0.0f;
      this.position = new Vec2();
   }

   public Pointer(Vec2 position, float angle) {
      this.angle = angle;
      this.position = new Vec2(position);
   }

   public void set(Vec2 position, float angle) {
      this.angle = angle;
      this.position.set(position);
   }

   public float getAngle() {
      return angle;
   }

   public Vec2 getDirection() {
      return new Vec2(MathUtils.cos(angle), MathUtils.sin(angle));
   }

   public Vec2 getPosition() {
      return position;
   }

   public Transform getTransform() {
      Transform result = new Transform();
      result.set(position, angle);
      return result;
   }

   public Pointer applyTransform(Transform transform) {
      float resultAngle = this.angle + transform.q.getAngle();
      Vec2 resultPosition = Transform.mul(transform, position);
      return new Pointer(resultPosition, resultAngle);
   }
   
   public Pointer applyScale(float scale) {
      return new Pointer(position.mul(scale), angle);
   }
}
