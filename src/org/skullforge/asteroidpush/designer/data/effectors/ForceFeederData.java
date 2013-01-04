package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.ForceFeeder;
import org.skullforge.asteroidpush.utils.Pointer;

public class ForceFeederData implements EffectorData {
   private Pointer anchor;
   private float magnitude;

   @Override
   public Effector createEffector(float size, Transform placement, Body body) {
      ForceFeeder feeder = new ForceFeeder();
      feeder.setPropulsee(body);
      Pointer pointer = anchor.applyScale(size);
      feeder.setPlacement(pointer.applyTransform(placement));
      feeder.setFacing(Facing.fromTransform(placement));
      feeder.setMagnitude(magnitude);
      return feeder;
   }

   public ForceFeederData() {
      this.anchor = new Pointer();
      this.magnitude = 0.0f;
   }

   public void setAnchor(Vec2 offset, float angle) {
      this.anchor.set(offset, angle);
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }
}