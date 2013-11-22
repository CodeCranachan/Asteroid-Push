package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.EntityEmitter;
import org.skullforge.asteroidpush.utils.Pointer;

public class EntityEmitterData implements EffectorData {
   private Pointer anchor;
   private float projectileVelocity;

   public Effector createEffector(float size, Transform placement, Body body) {
      EntityEmitter emitter = new EntityEmitter();
      emitter.setShooter(body);
      Pointer pointer = anchor.applyScale(size);
      pointer = pointer.applyTransform(placement);
      emitter.setPlacement(pointer);
      emitter.setVelocity(projectileVelocity);
      return emitter;
   }

   public EntityEmitterData() {
      this.anchor = new Pointer();
      this.projectileVelocity = 0.0f;
   }

   public void setAnchor(Vec2 offset, float angle) {
      this.anchor.set(offset, angle);
   }

   public void setProjectileVelocity(float velocity) {
      this.projectileVelocity = velocity;
   }
}