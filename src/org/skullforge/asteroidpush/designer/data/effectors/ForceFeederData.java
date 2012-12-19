package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.ForceFeeder;

public class ForceFeederData implements EffectorData {
   private Transform anchor;
   private float magnitude;

   @Override
   public Effector createEffector(Transform transform, Body body) {
      ForceFeeder feeder = new ForceFeeder(this);
      feeder.setPropulsee(body);
      feeder.setPlacement(transform);
      feeder.setFacing(Facing.fromTransform(transform));
      return feeder;
   }

   public ForceFeederData() {
      this.anchor = new Transform();
      this.magnitude = 0.0f;
   }

   public Vec2 getForce() {
      Vec2 force = new Vec2(1.0f, 0.0f);
      force = anchor.R.mul(force);
      force.mulLocal(magnitude);
      return force;
   }

   public Vec2 getOffset() {
      return anchor.position;
   }

   public void setAnchor(Vec2 offset, float angle) {
      this.anchor.set(offset, angle);
   }

   public void setMagnitude(float magnitude) {
      this.magnitude = magnitude;
   }
}