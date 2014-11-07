package org.codecranachan.asteroidpush.utils;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class NewtonianState {
   private Vec2 position;
   private Angle rotation;
   private Vec2 linearVelocity;
   private Angle angularVelocity;

   public NewtonianState() {
      this.position = new Vec2();
      this.rotation = new Angle();
      this.linearVelocity = new Vec2();
      this.angularVelocity = new Angle();
   }

   public void setState(Vec2 position, Angle rotation) {
      this.position.set(position);
      this.rotation.set(rotation);
   }

   public void setVelocity(Vec2 linear, Angle angular) {
      this.linearVelocity.set(linear);
      this.angularVelocity.set(angular);
   }

   public Arrow getState() {
      return new Arrow(this.position, this.rotation);
   }

   public Arrow getVelocity() {
      return new Arrow(this.linearVelocity, this.angularVelocity);
   }

   public NewtonianState transform(Arrow offset) {
      NewtonianState transformed = new NewtonianState();
      Vec2 transformedPosition = Transform.mul(offset.getTransform(), position);
      transformed.setState(transformedPosition,
                           rotation.add(offset.getAngle()));
      transformed.setVelocity(offset.getAngle().rotate(linearVelocity),
                              angularVelocity);
      return transformed;
   }

   public Vec2 getPosition() {
      return position;
   }

   public Angle getRotation() {
      return rotation;
   }

   public Vec2 getLinearVelocity() {
      return linearVelocity;
   }

   public Angle getAngularVelocity() {
      return angularVelocity;
   }
}