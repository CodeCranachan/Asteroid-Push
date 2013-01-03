package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.designer.data.effectors.ForceFeederData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.utils.Pointer;

public class ForceFeeder implements Effector {

   final private ForceFeederData data;
   private Body propulsee;
   private Transform placement;
   private Facing facing;

   public ForceFeeder(ForceFeederData data) {
      this.data = data;
      this.placement = new Transform();
      this.propulsee = null;
      this.facing = Facing.FORWARD;
   }

   @Override
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

      Pointer pointer = data.getAnchor();
      pointer = pointer.applyTransform(placement);
      pointer = pointer.applyTransform(propulsee.getTransform());

      Vec2 force = pointer.getDirection();
      force = force.mul(data.getMagnitude());
      force = force.mul(signal);

      propulsee.applyForce(force, pointer.getPosition());

      return null;
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   public void setPlacement(Transform transform) {
      this.placement.set(transform);
   }

   public void setFacing(Facing facing) {
      this.facing = facing;
   }

}
