package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;
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
   public Collection<SimulatorCommand> update(int frameNumber, SignalController controller) {
      float signal = 0.0f;
      if (controller != null) {
         signal += controller.anticlockwiseThrust;
         signal -= controller.clockwiseThrust;
      }

      float torque = data.getMagnitude() * signal;

      propulsee.applyTorque(torque);
      
      return null;
   }
}
