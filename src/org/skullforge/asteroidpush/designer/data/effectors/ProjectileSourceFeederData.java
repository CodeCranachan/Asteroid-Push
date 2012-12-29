package org.skullforge.asteroidpush.designer.data.effectors;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.entities.spaceship.Effector;
import org.skullforge.asteroidpush.entities.spaceship.ProjectileSourceFeeder;

public class ProjectileSourceFeederData implements EffectorData {
   private Transform anchor;
   private float projectileVelocity;

   @Override
   public Effector createEffector(Transform transform, Body body) {
      ProjectileSourceFeeder feeder = new ProjectileSourceFeeder(this);
      feeder.setShooter(body);
      feeder.setPlacement(transform);
      feeder.setFacing(Facing.fromTransform(transform));
      return feeder;
   }

   public ProjectileSourceFeederData() {
      this.anchor = new Transform();
      this.projectileVelocity = 0.0f;
   }

   public Vec2 getForce() {
      Vec2 force = new Vec2(1.0f, 0.0f);
      force = anchor.R.mul(force);
      force.mulLocal(projectileVelocity);
      return force;
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