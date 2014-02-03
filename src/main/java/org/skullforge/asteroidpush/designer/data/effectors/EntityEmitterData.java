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

package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.EntityEmitter;
import org.skullforge.asteroidpush.utils.Pointer;

public class EntityEmitterData implements EffectorData {
   private Pointer anchor;
   private float projectileVelocity;

   public Effector createEffector(float size, Transform placement, Body body) {
      EntityEmitter emitter = new EntityEmitter();
      emitter.setShooter(body);
      Pointer pointer = anchor.applyScale(size);
      pointer = pointer.applyTransform(placement);
      emitter.setPlacement(pointer);
      emitter.setVelocity(projectileVelocity);
      return emitter;
   }

   public EntityEmitterData() {
      this.anchor = new Pointer();
      this.projectileVelocity = 0.0f;
   }

   public void setAnchor(Vec2 offset, float angle) {
      this.anchor.set(offset, angle);
   }

   public void setProjectileVelocity(float velocity) {
      this.projectileVelocity = velocity;
   }
}