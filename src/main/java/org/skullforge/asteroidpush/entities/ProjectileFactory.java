package org.skullforge.asteroidpush.entities;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class ProjectileFactory implements EntityFactory {
   final World world;
   final Vec2 velocity;
   final float angle;

   public ProjectileFactory(World world, Vec2 velocity, float angle) {
      this.world = world;
      this.velocity = velocity;
      this.angle = angle;
   }

   public Entity createEntity(Vec2 location) {
      Body body = world.createBody(getBodyDef(location));
      body.createFixture(getFixtureDef());
      Projectile entity = new Projectile(body, 300);
      return entity;
   }

   private BodyDef getBodyDef(Vec2 location) {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(location);
      def.linearDamping = 0.05f;
      def.angularDamping = 0.01f;
      def.fixedRotation = false;
      def.linearVelocity = velocity;
      def.angle = angle;
      def.bullet = false;
      return def;
   }

   private FixtureDef getFixtureDef() {
      FixtureDef def = new FixtureDef();
      def.density = 500.0f;
      def.friction = 0.1f;
      def.restitution = 0.8f;
      def.shape = getShape();
      return def;
   }

   private Shape getShape() {
      CircleShape shape = new CircleShape();
      shape.m_radius = 0.1f;
      return shape;
   }

}
