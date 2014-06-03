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

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.SignalController;
import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.SpawnEntityCommand;
import org.codecranachan.asteroidpush.entities.ProjectileFactory;
import org.codecranachan.asteroidpush.utils.Pointer;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class EntityEmitter implements Effector {

   private Body shooter;
   final private Pointer placement;
   private float emitterVelocity;
   private boolean shotHandled;

   public EntityEmitter() {
      this.placement = new Pointer();
      this.shooter = null;
      this.emitterVelocity = 0.0f;
      this.shotHandled = false;
   }

   public Collection<SimulatorCommand> update(int frameNumber,
                                              SignalController controller) {
      if (controller.primaryFire == 0.0f) {
         this.shotHandled = false;
      } else if (!this.shotHandled) {

         Pointer pointer = placement.applyTransform(shooter.getTransform());

         Vec2 velocity = pointer.getDirection();
         velocity = velocity.mul(emitterVelocity);
         velocity.addLocal(shooter.getLinearVelocityFromLocalPoint(placement.getPosition()));

         ArrayList<SimulatorCommand> commands = new ArrayList<SimulatorCommand>();
         ProjectileFactory factory = new ProjectileFactory(shooter.getWorld(),
               velocity, pointer.getAngle());
         commands.add(new SpawnEntityCommand(factory, pointer.getPosition(),
               null));
         this.shotHandled = true;
         return commands;
      }
      return null;
   }

   public void setShooter(Body shooter) {
      this.shooter = shooter;
   }

   public void setPlacement(Pointer placement) {
      this.placement.set(placement.getPosition(), placement.getAngle());
   }

   public void setVelocity(float velocity) {
      this.emitterVelocity = velocity;
   }

}
