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
import org.newdawn.slick.geom.Transform;
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
         Transform t = convertToSlickTransform(b.getTransform());
         while (f != null) {
            Shape shape = convertToSlickShape(f.getShape());
            shape = shape.transform(t);
            silhouette.add(shape);
            f = f.getNext();
         }
      }

      return silhouette;
   }

   private org.newdawn.slick.geom.Transform convertToSlickTransform(org.jbox2d.common.Transform transform) {
      Transform converted = new Transform(transform.R.col1.x,
            transform.R.col2.x, transform.position.x, transform.R.col1.y,
            transform.R.col2.y, transform.position.y);
      return converted;
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
         converted = convertToPolygon(shape);
         break;
      case CIRCLE:
         converted = convertToCircle(shape);
         break;
      default:
         converted = new Circle(0.0f, 0.0f, 7.5f);
         break;
      }

      return converted;
   }

   private org.newdawn.slick.geom.Shape convertToCircle(org.jbox2d.collision.shapes.Shape shape) {
      org.newdawn.slick.geom.Shape converted;
      CircleShape circle = (CircleShape) shape;
      converted = new Circle(circle.m_p.x, circle.m_p.y, circle.m_radius);
      return converted;
   }

   private org.newdawn.slick.geom.Shape convertToPolygon(org.jbox2d.collision.shapes.Shape shape) {
      PolygonShape poly = (PolygonShape) shape;
      Vec2[] vertices = poly.getVertices();
      float[] points = new float[poly.getVertexCount() * 2];
      for (int i = 0; i < poly.getVertexCount(); ++i) {
         points[i * 2 + 0] = vertices[i].x;
         points[i * 2 + 1] = vertices[i].y;
      }
      org.newdawn.slick.geom.Polygon converted = new Polygon(points);
      return converted;
   }

   private Part part;
}
