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

package org.codecranachan.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.codecranachan.asteroidpush.SignalController;
import org.codecranachan.asteroidpush.SimulatorCommand;
import org.jbox2d.dynamics.Body;

public class TorqueFeeder implements Effector {

   private float magnitude;
   private Body propulsee;

   public TorqueFeeder() {
      this.magnitude = 0.0f;
      this.propulsee = null;
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }

   public Collection<SimulatorCommand> update(int frameNumber,
                                              SignalController controller) {
      float signal = 0.0f;
      if (controller != null) {
         signal += controller.anticlockwiseThrust;
         signal -= controller.clockwiseThrust;
      }

      float torque = magnitude * signal;

      propulsee.applyTorque(torque);

      return null;
   }
}
