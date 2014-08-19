package org.codecranachan.asteroidpush.simulation;

import java.util.Vector;

import org.codecranachan.asteroidpush.utils.Arrow;
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

   public void AddCircle(Arrow geometry, int points) {
      for (int i = 0; i < points; ++i) {
         float angle = MathUtils.TWOPI * i / points + geometry.getAngle();
         Vec2 vertice = new Vec2(
               MathUtils.cos(angle) * geometry.getMagnitude(),
               MathUtils.sin(angle) * geometry.getMagnitude());
         vertice.addLocal(geometry.getTail());
         vertices.add(vertice);
      }
   }

   public void AddSquare(Arrow geometry) {
      float diagonal = MathUtils.sqrt(geometry.getMagnitude()
            * geometry.getMagnitude() * 2);
      Arrow circle_geometry = new Arrow(geometry.getTail(), geometry.getAngle()
            + MathUtils.QUARTER_PI, diagonal);
      AddCircle(circle_geometry, 4);
   }

   public Vector<Vec2> getVertices() {
      return vertices;
   }

}
