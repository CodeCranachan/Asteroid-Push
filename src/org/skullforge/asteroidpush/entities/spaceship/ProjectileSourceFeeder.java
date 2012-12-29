package org.skullforge.asteroidpush.entities.spaceship;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.SpawnEntityCommand;
import org.skullforge.asteroidpush.designer.data.effectors.ProjectileSourceFeederData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.entities.StaticMarkerFactory;

public class ProjectileSourceFeeder implements Effector {

   final private ProjectileSourceFeederData data;
   private Body shooter;
   private Transform placement;
   private Facing facing;
   private boolean shotHandled;

   public ProjectileSourceFeeder(ProjectileSourceFeederData data) {
      this.data = data;
      this.placement = new Transform();
      this.shooter = null;
      this.facing = Facing.FORWARD;
      this.shotHandled = false;
   }

   @Override
   public Collection<SimulatorCommand> update(int frameNumber, SignalController controller) {
      if (controller.primaryFire == 0.0f) {
         this.shotHandled = false;
      } else if (!this.shotHandled) {
         Transform shooterTransform = shooter.getTransform();

         Vec2 offset = data.getOffset();
         offset = Transform.mul(placement, offset);
         offset = Transform.mul(shooterTransform, offset);

         ArrayList<SimulatorCommand> commands = new ArrayList<SimulatorCommand>();
         commands.add( new SpawnEntityCommand(new StaticMarkerFactory(shooter.getWorld()), offset, null));
         this.shotHandled = true;
         return commands;
      }
      return null;
   }

   public void setShooter(Body shooter) {
      this.shooter = shooter;
   }

   public void setPlacement(Transform transform) {
      this.placement.set(transform);
   }

   public void setFacing(Facing facing) {
      this.facing = facing;
   }

}
