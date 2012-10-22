package org.skullforge.asteroidpush.parts;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

public class Block implements Part {

   public Block(Vec2 position, Material material) {
      this.body = null;
      this.spawnPosition = new Vec2(position);
      this.material = material;
   }

   @Override
   public void spawn(World world) {
      if (body == null) {
         body = world.createBody(getBodyDef());
         body.createFixture(getFixtureDef());
      }
   }

   @Override
   public void despawn(World world) {
      if (body != null) {
         world.destroyBody(body);
         body = null;
      }
   }

   @Override
   public ArrayList<Body> getBodies() {
      ArrayList<Body> bodies = new ArrayList<Body>();
      if (body != null) {
         bodies.add(body);
      }
      return bodies;
   }

   @Override
   public ArrayList<Joint> getJoints() {
      return new ArrayList<Joint>();
   }

   private BodyDef getBodyDef() {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(spawnPosition);
      def.linearDamping = 0.01f;
      def.angularDamping = 0.01f;
      def.fixedRotation = false;
      return def;
   }

   private FixtureDef getFixtureDef() {
      FixtureDef def = new FixtureDef();
      def.density = material.density;
      def.friction = material.friction;
      def.restitution = material.restitution;
      def.shape = getShape();
      return def;
   }

   private Shape getShape() {
      PolygonShape shape = new PolygonShape();
      Vec2 vertices[] = new Vec2[] { new Vec2(0.25f, 0.25f),
            new Vec2(-0.25f, 0.25f), new Vec2(-0.25f, -0.25f),
            new Vec2(0.25f, -0.25f) };

      shape.set(vertices, vertices.length);
      return shape;
   }

   Vec2 spawnPosition;
   Material material;
   Body body;
}
