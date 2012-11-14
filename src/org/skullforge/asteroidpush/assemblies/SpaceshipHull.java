package org.skullforge.asteroidpush.assemblies;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.skullforge.asteroidpush.designer.Module;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.grid.Coordinate;

public class SpaceshipHull implements Assembly {

   public SpaceshipHull(Vec2 position, ShipDesign design) {
      this.spawnPosition = new Vec2(position);
      this.design = design;
      this.body = null;
   }

   @Override
   public void spawn(World world) {
      if (body == null) {
         body = world.createBody(getBodyDef());
         for (Module module : design.getModules()) {
            body.createFixture(getFixtureDef(module));
         }
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
      def.angle = MathUtils.HALF_PI;
      def.linearDamping = 0.1f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;
      return def;
   }

   private FixtureDef getFixtureDef(Module module) {
      FixtureDef def = new FixtureDef();
      def.density = Material.METAL.density;
      def.friction = Material.METAL.friction;
      def.restitution = Material.METAL.restitution;
      def.shape = getShape(module.getPlacement().getCoordinate());
      return def;
   }

   private Shape getShape(Coordinate coordinate) {
      PolygonShape shape = new PolygonShape();
      float xOffset = standardModuleSize * coordinate.getX();
      float yOffset = standardModuleSize * coordinate.getY();
      float moduleOffset = standardModuleSize / 2.0f;
      Vec2 vertices[] = new Vec2[] {
            new Vec2(xOffset + moduleOffset, yOffset + moduleOffset),
            new Vec2(xOffset - moduleOffset, yOffset + moduleOffset),
            new Vec2(xOffset - moduleOffset, yOffset - moduleOffset),
            new Vec2(xOffset + moduleOffset, yOffset - moduleOffset) };

      shape.set(vertices, vertices.length);
      return shape;
   }

   private final float standardModuleSize = 0.5f;
   private Body body;
   private Vec2 spawnPosition;
   private ShipDesign design;
}