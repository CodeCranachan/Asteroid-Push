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

package org.codecranachan.asteroidpush.designer.data.effectors;

import org.codecranachan.asteroidpush.designer.data.EffectorData;
import org.codecranachan.asteroidpush.entities.spaceship.Effector;
import org.codecranachan.asteroidpush.entities.spaceship.TorqueFeeder;
import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;

public class TorqueFeederData implements EffectorData {

   private float magnitude;

   public Effector createEffector(float size, Transform placement, Body body) {
      TorqueFeeder feeder = new TorqueFeeder();
      feeder.setPropulsee(body);
      feeder.setMagnitude(magnitude);
      return feeder;
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }

   public float getMagnitude() {
      return this.magnitude;
   }

}