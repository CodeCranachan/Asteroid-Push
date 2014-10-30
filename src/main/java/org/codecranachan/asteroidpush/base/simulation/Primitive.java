package org.codecranachan.asteroidpush.base.simulation;

import java.util.Vector;

import org.codecranachan.asteroidpush.utils.Circle;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class Primitive {
   private Vector<Vec2> vertices;

   public Primitive() {
      vertices = new Vector<Vec2>();
   }

   public void AddVertex(Vec2 vertex) {
      assert (vertex != null);
      vertices.add(vertex);
   }

   public void AddCircle(Circle geometry, int points) {
      for (int i = 0; i < points; ++i) {
         float angle = MathUtils.TWOPI * i / points;
         Vec2 vertice = new Vec2(MathUtils.cos(angle) * geometry.getRadius(),
               MathUtils.sin(angle) * geometry.getRadius());
         vertice.addLocal(geometry.getCenter());
         vertices.add(vertice);
      }
   }

   public void AddSquare(Vec2 center, float a) {
      float d = a / 2f;
      vertices.add(new Vec2(center.x + d, center.y + d));
      vertices.add(new Vec2(center.x - d, center.y + d));
      vertices.add(new Vec2(center.x - d, center.y - d));
      vertices.add(new Vec2(center.x + d, center.y - d));
   }

   public Vector<Vec2> getVertices() {
      return vertices;
   }

}
