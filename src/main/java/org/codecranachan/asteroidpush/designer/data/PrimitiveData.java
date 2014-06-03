//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.designer.data;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

public class PrimitiveData {
   private Material material;
   private Vec2[] shape;

   public FixtureDef getFixtureDef(Transform transform, float size) {
      FixtureDef def = new FixtureDef();
      def.density = material.density;
      def.friction = material.friction;
      def.restitution = material.restitution;
      def.shape = getShape(transform, size);
      return def;
   }

   public void setMaterial(Material material) {
      this.material = material;
   }

   public void setVertices(Vec2[] shape) {
      this.shape = shape;
   }

   public Vec2[] getVertices() {
      return this.shape;
   }

   public float[] getPointArray() {
      float points[] = new float[shape.length * 2];
      for (int i = 0; i < shape.length; ++i) {
         points[i * 2] = shape[i].x;
         points[i * 2 + 1] = shape[i].y;
      }
      return points;
   }

   public PolygonShape getShape(Transform transform, float size) {
      Vec2 vertices[] = new Vec2[shape.length];
      for (int i = 0; i < shape.length; ++i) {
         vertices[i] = shape[i].mul(size);
         vertices[i] = Transform.mul(transform, vertices[i]);
      }
      PolygonShape polygon = new PolygonShape();
      polygon.set(vertices, shape.length);
      return polygon;
   }

}
