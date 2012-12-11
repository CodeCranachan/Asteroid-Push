package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.designer.data.effectors.ForceFeederData;

public class ForceFeeder implements Effector {

   final private ForceFeederData data;
   private Body propulsee;
   private Transform placement;

   public ForceFeeder(ForceFeederData data) {
      this.data = data;
      this.placement = new Transform();
      this.propulsee = null;
   }

   @Override
   public void update(int frameNumber, SignalController controller) {
      float signal = 1.0f;
      if (controller != null) {
         signal = controller.forwardThrust;
      }

      Transform bodyTransform = propulsee.getTransform();

      Vec2 force = data.getForce();
      force.mulLocal(signal);
      force = placement.R.mul(force);
      force = bodyTransform.R.mul(force);

      Vec2 offset = data.getOffset();
      offset = Transform.mul(placement, offset);
      offset = Transform.mul(bodyTransform, offset);

      propulsee.applyForce(force, offset);
   }

   public void setPropulsee(Body propulsee) {
      this.propulsee = propulsee;
   }

   public void setPlacement(Transform transform) {
      this.placement.set(transform);
   }

}
