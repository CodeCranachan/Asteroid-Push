package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.common.Mat22;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;

public class Thruster implements Effector {

   public Thruster() {
      this.propulsee = null;
      this.anchor = new Vec2();
      this.magnitude = 0.0f;
      this.angle = 0.0f;
   }

   @Override
   public void update(int frameNumber, SignalController controller) {
      if (controller == null) {
         return;
      }

      Mat22 rotation = new Mat22();
      rotation.set(propulsee.getAngle() + angle);

      Vec2 force = new Vec2(1.0f, 0.0f);
      force.mulLocal(magnitude);
      force.mulLocal(controller.forwardThrust);
      force = rotation.mul(force);

      Vec2 point = propulsee.getWorldPoint(anchor);
      propulsee.applyForce(force, point);
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   public void setAnchor(Vec2 anchor) {
      this.anchor.set(anchor);
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }

   public void setAngle(float angle) {
      this.angle = angle;
   }

   private Body propulsee;
   private Vec2 anchor;
   private float magnitude;
   private float angle;
}
