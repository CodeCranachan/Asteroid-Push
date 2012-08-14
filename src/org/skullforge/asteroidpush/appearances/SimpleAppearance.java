package org.skullforge.asteroidpush.appearances;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.parts.Part;

public class SimpleAppearance implements Appearance {

   public SimpleAppearance(Part part) {
      this.part = part;
   }

   @Override
   public ArrayList<Shape> getSilhouette() {
      ArrayList<Body> bodies = part.getBodies();
      ArrayList<Shape> silhouette = new ArrayList<Shape>(bodies.size());

      for (Body b : bodies) {
         Fixture f = b.getFixtureList();
         while (f != null) {
            Shape shape = convertToSlickShape(f.getShape());
            silhouette.add(shape);
            f = f.getNext();
         }
      }

      return silhouette;
   }

   public org.newdawn.slick.geom.Shape convertToSlickShape(org.jbox2d.collision.shapes.Shape shape) {
      org.newdawn.slick.geom.Shape converted;

      ShapeType type;
      if (shape == null) {
         type = ShapeType.UNKNOWN;
      } else {
         type = shape.getType();
      }

      switch (type) {
      case POLYGON:
         PolygonShape poly = (PolygonShape) shape;
         Vec2[] vertices = poly.getVertices();
         float[] points = new float[vertices.length * 2];
         for (int i = 0; i < points.length; ++i) {
            if (i % 2 == 0) {
               points[i] = vertices[i / 2].x;
            } else {
               points[i] = vertices[i / 2].y;
            }
         }
         converted = new Polygon(points);
         break;
      case CIRCLE:
         CircleShape circle = (CircleShape) shape;
         converted = new Circle(circle.m_p.x, circle.m_p.y, circle.m_radius);
         break;
      default:
         converted = new Circle(0.0f, 0.0f, 7.5f);
         break;
      }

      return converted;
   }

   private Part part;
}
