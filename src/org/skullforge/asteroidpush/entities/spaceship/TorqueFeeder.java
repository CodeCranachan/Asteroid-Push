package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.designer.data.effectors.TorqueFeederData;

public class TorqueFeeder implements Effector {

   private TorqueFeederData data;
   private Body propulsee;

   public TorqueFeeder(TorqueFeederData data) {
      this.data = data;
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   @Override
   public void update(int frameNumber, SignalController controller) {
      float signal = 0.0f;
      if (controller != null) {
         signal += controller.anticlockwiseThrust;
         signal -= controller.clockwiseThrust;
      }

      float torque = data.getMagnitude() * signal;

      propulsee.applyTorque(torque);
   }
}
