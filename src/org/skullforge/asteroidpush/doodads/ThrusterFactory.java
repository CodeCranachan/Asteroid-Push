package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Transform;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorFactory;
import org.skullforge.asteroidpush.logic.Thruster;

public class ThrusterFactory implements EffectorFactory {

   @Override
   public Effector create(Transform transform, Body body) {
      Thruster thruster = new Thruster();

      thruster.setPropulsee(body);
      thruster.setAnchor(transform.position);
      thruster.setAngle(transform.R.getAngle());
      thruster.setMagnitude(50000.0f);

      return thruster;
   }

}
