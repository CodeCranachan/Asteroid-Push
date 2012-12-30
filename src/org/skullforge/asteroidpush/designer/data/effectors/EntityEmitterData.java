package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.EntityEmitter;

public class EntityEmitterData implements EffectorData {
   private Transform anchor;
   private float projectileVelocity;

   @Override
   public Effector createEffector(Transform transform, Body body) {
      EntityEmitter feeder = new EntityEmitter(this);
      feeder.setShooter(body);
      feeder.setPlacement(transform);
      return feeder;
   }

   public EntityEmitterData() {
      this.anchor = new Transform();
      this.projectileVelocity = 0.0f;
   }

   public Vec2 getVelocity() {
      Vec2 velocity = new Vec2(1.0f, 0.0f);
      velocity = anchor.R.mul(velocity);
      velocity.mulLocal(projectileVelocity);
      return velocity;
   }

   public Vec2 getOffset() {
      return anchor.position;
   }

   public void setAnchor(Vec2 offset, float angle) {
      this.anchor.set(offset, angle);
   }

   public void setProjectileVelocity(float velocity) {
      this.projectileVelocity = velocity;
   }
}