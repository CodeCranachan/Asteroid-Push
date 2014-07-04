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

package org.codecranachan.asteroidpush.entities;

import java.util.ArrayList;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PlayingFieldBorderFactory implements EntityFactory {

   final private float borderThickness = 10.0f;

   private float fieldHeight;

   private float fieldWidth;

   private Vec2 innerDiagonal;

   private Vec2 outerDiagonal;

   final private World world;

   public PlayingFieldBorderFactory(World world) {
      this.world = world;
      fieldWidth = 100.0f;
      fieldHeight = 50.0f;
   }

   public Entity createEntity(Arrow location) {
      return null;
   }

   public Entity createEntity(Vec2 location) {
      Vec2 border = new Vec2(borderThickness, borderThickness);
      innerDiagonal = new Vec2(fieldWidth / 2.0f, fieldHeight / 2.0f);
      outerDiagonal = innerDiagonal.add(border);

      Body body = world.createBody(getBodyDef());
      for (PolygonShape shape : getShapes()) {
         FixtureDef def = new FixtureDef();
         def.shape = shape;
         body.createFixture(def);
      }

      Entity entity = new PassiveObject(body);
      return entity;
   }

   private BodyDef getBodyDef() {
      return new BodyDef();
   }

   private ArrayList<PolygonShape> getShapes() {
      ArrayList<PolygonShape> shapeList = new ArrayList<PolygonShape>();
      Vec2 boxes[][];

      boxes = new Vec2[][] {
            {
                  new Vec2(innerDiagonal.x, innerDiagonal.y),
                  new Vec2(innerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y)
            },
            {
                  new Vec2(-innerDiagonal.x, innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-innerDiagonal.x, -innerDiagonal.y)
            },
            {
                  new Vec2(outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y)
            },
            {
                  new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -outerDiagonal.y),
                  new Vec2(outerDiagonal.x, -outerDiagonal.y)
            }
      };

      for (Vec2 vertices[] : boxes) {
         PolygonShape shape;
         shape = new PolygonShape();
         shape.set(vertices, vertices.length);
         shapeList.add(shape);
      }
      return shapeList;
   }

   public void setParameters(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }
}
