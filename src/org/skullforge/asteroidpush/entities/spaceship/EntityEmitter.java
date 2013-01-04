package org.skullforge.asteroidpush.entities.spaceship;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.SpawnEntityCommand;
import org.skullforge.asteroidpush.entities.ProjectileFactory;
import org.skullforge.asteroidpush.utils.Pointer;

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

   @Override
   public Collection<SimulatorCommand> update(int frameNumber,
                                              SignalController controller) {
      if (controller.primaryFire == 0.0f) {
         this.shotHandled = false;
      } else if (!this.shotHandled) {

         Pointer pointer = placement.applyTransform(shooter.getTransform());

         Vec2 velocity = pointer.getDirection();
         velocity = velocity.mul(emitterVelocity);
         velocity.addLocal(shooter.getLinearVelocity());

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
