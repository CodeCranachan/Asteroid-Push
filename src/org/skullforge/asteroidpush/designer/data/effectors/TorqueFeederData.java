package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.TorqueFeeder;

public class TorqueFeederData implements EffectorData {

   private float magnitude;

   @Override
   public Effector createEffector(Transform transform, Body body) {
      TorqueFeeder feeder = new TorqueFeeder(this);
      feeder.setPropulsee(body);
      return feeder;
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }

   public float getMagnitude() {
      return this.magnitude;
   }

}
