package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.SimulatorCommand;

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
