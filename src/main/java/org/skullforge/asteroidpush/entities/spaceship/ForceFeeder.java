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

package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.utils.Pointer;

public class ForceFeeder implements Effector {

   private Body propulsee;
   private Pointer placement;
   private Facing facing;
   private float magnitude;

   public ForceFeeder() {
      this.placement = new Pointer();
      this.propulsee = null;
      this.facing = Facing.FORWARD;
      this.magnitude = 0.0f;
   }

   public Collection<SimulatorCommand> update(int frameNumber,
                                              SignalController controller) {
      float signal = 1.0f;
      if (controller != null) {
         switch (facing) {
         case FORWARD:
            signal = controller.forwardThrust;
            break;
         case BACKWARD:
            signal = controller.backwardThrust;
            break;
         case LEFT:
            signal = controller.leftThrust;
            break;
         case RIGHT:
            signal = controller.rightThrust;
            break;
         }
      }

      Pointer pointer = this.placement.applyTransform(propulsee.getTransform());
      Vec2 force = pointer.getDirection();
      force = force.mul(signal * magnitude);
      propulsee.applyForce(force, pointer.getPosition());

      return null;
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   public void setPlacement(Pointer pointer) {
      this.placement.set(pointer.getPosition(), pointer.getAngle());
   }

   public void setFacing(Facing facing) {
      this.facing = facing;
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }
}
