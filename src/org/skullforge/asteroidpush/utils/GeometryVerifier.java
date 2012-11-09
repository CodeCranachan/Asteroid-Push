package org.skullforge.asteroidpush.utils;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

public class GeometryVerifier {
   /**
    * Checks a jbox2d body for data sanity.
    * 
    * This will verify whether a jbox2d Body has been created correctly. It
    * currently only checks PolygonShapes for correct vertex winding order.
    * 
    * @param body
    *           the body to be checked
    * @return true of the body is sane, false otherwise
    */
   public static boolean IsBodySane(Body body) {
      Fixture fixture = body.getFixtureList();

      while (fixture != null) {
         if (fixture.getType() == ShapeType.POLYGON) {
            if (!IsWoundCorrectly((PolygonShape) fixture.getShape())) {
               return false;
            }
         }
         fixture = fixture.getNext();
      }

      return true;
   }

   /**
    * Tests the winding of a jbox2d Polygon shape, will also automatically
    * verify whether it is concave.
    * 
    * @param shape
    *           the shape to be checked
    * @return true if the shape is wound correctly, false otherwise
    */
   public static boolean IsWoundCorrectly(PolygonShape shape) {
      int n = shape.m_vertexCount;

      if (n < 3) {
         return false;
      }

      for (int i = 0; i < n; ++i) {
         int k = i + n;
         Vec2 a = shape.m_vertices[(k - 1) % n].sub(shape.m_vertices[(k) % n]);
         Vec2 b = shape.m_vertices[(k) % n].sub(shape.m_vertices[(k + 1) % n]);
         if (Vec2.cross(a, b) <= 0.0f) {
            return false;
         }
      }

      return true;
   }
}