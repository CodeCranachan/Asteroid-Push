package org.skullforge.asteroidpush.arena.entities;

import java.util.Vector;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Polygon;
import org.skullforge.asteroidpush.arena.Entity;
import org.skullforge.asteroidpush.arena.Viewport;

public class Scenery implements Entity {

   public Scenery() {
      body = null;
      geometry = CreateGeometry();
   }

   @Override
   public void spawn(World world, Vec2 position) {
      body = world.createBody(GetBodyDef(position));
      AddFixturesToBody();
   }

   @Override
   public void render(Viewport view) {
      if (body != null) {
         for (Polygon poly : geometry) {
            view.drawPolygon(poly);
         }
      }
   }

   @Override
   public void update(int delta) {
      // Does not do anything
   }

   @Override
   public Vec2 getPosition() {
      if (body != null) {
         return body.getPosition();
      } else {
         return new Vec2(0.0f, 0.0f);
      }
   }

   private BodyDef GetBodyDef(Vec2 position) {
      BodyDef def = new BodyDef();
      def.position.set(position);
      def.type = BodyType.STATIC;
      return def;
   }

   private void AddFixturesToBody() {
      for (Polygon poly : geometry) {
         Vec2 vertices[] = new Vec2[poly.getPointCount()];
         for (int i = 0; i < poly.getPointCount(); ++i) {
            float point[] = poly.getPoint(i);
            vertices[i] = new Vec2(point[0], point[1]);
         }
         PolygonShape shape = new PolygonShape();
         shape.set(vertices, poly.getPointCount());
         body.createFixture(shape, 0.0f);
      }
   }

   private Vector<Polygon> CreateGeometry() {
      Vector<Polygon> geo = new Vector<Polygon>();

      Polygon poly;

      poly = new Polygon();
      poly.addPoint(-25.0f, 10.0f);
      poly.addPoint(25.0f, 10.0f);
      poly.addPoint(25.0f, 11.0f);
      poly.addPoint(-25.0f, 11.0f);
      poly.setClosed(true);
      geo.add(poly);

      poly = new Polygon();
      poly.addPoint(4.5f, 6.0f);
      poly.addPoint(5.0f, 5.0f);
      poly.addPoint(6.5f, 6.0f);
      poly.addPoint(6.0f, 7.0f);
      poly.addPoint(5.0f, 8.0f);
      poly.addPoint(4.5f, 7.0f);
      poly.setClosed(true);
      geo.add(poly);

      return geo;
   }

   private Body body;
   private Vector<Polygon> geometry;
}
