package org.skullforge.asteroidpush.logic;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.assemblies.Assembly;

public class Thruster implements Logic {

   public Thruster(Assembly propulsee) {
      this.propulsee = propulsee;
   }

   @Override
   public void update(int frameNumber, SignalController controller) {
      if (controller == null) {
         return;
      }

      Vec2 backwardThrustDirection = new Vec2(-1.0f, 0.0f)
            .mul(controller.backwardThrust);
      Vec2 forwardThrustDirection = new Vec2(1.0f, 0.0f)
            .mul(controller.forwardThrust);
      Vec2 leftThrustDirection = new Vec2(0.0f, -1.0f)
            .mul(controller.leftThrust);
      Vec2 rightThrustDirection = new Vec2(0.0f, 1.0f)
            .mul(controller.rightThrust);

      float forceMagnitude = 75000.0f;
      Vec2 force = new Vec2();
      force.addLocal(backwardThrustDirection);
      force.addLocal(forwardThrustDirection);
      force.addLocal(leftThrustDirection);
      force.addLocal(rightThrustDirection);
      force.mulLocal(forceMagnitude);
      float torque = controller.clockwiseThrust
            - controller.anticlockwiseThrust;
      torque = torque * 7500.0f;

      Collection<Body> bodies = propulsee.getBodies();
      for (Body body : bodies) {
         Vec2 thrustForce = body.getTransform().R.mul(force);
         body.applyForce(thrustForce.mul(bodies.size()), body.getWorldCenter());
         body.applyTorque(torque / bodies.size());
      }
   }

   private Assembly propulsee;
}
