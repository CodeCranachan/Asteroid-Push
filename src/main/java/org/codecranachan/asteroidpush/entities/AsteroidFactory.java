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

import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class AsteroidFactory implements EntityFactory {
   final World world;

   public AsteroidFactory(World world) {
      this.world = world;
   }

   public Entity createEntity(Arrow location) {
      return null;
   }
   
   public Entity createEntity(Vec2 location) {
      Body body = world.createBody(getBodyDef(location));
      body.createFixture(getFixtureDef());
      PassiveObject entity = new PassiveObject(body);
      return entity;
   }

   private BodyDef getBodyDef(Vec2 location) {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(location);
      def.linearDamping = 0.05f;
      def.angularDamping = 0.01f;
      def.fixedRotation = false;
      return def;
   }

   private FixtureDef getFixtureDef() {
      FixtureDef def = new FixtureDef();
      def.density = 2800.0f;
      def.friction = 0.2f;
      def.restitution = 0.2f;
      def.shape = getShape();
      return def;
   }

   private Shape getShape() {
      PolygonShape shape = new PolygonShape();
      Vec2 vertices[] = new Vec2[] {
            new Vec2(0.0f, -2.0f),
            new Vec2(1.2f, -1.7f),
            new Vec2(1.3f, 2.1f),
            new Vec2(-0.2f, 2.4f),
            new Vec2(-3.4f, 0.2f),
            new Vec2(-3.0f, -0.7f)
      };
      shape.set(vertices, vertices.length);
      return shape;
   }

}
