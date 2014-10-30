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

package org.codecranachan.asteroidpush.content.actors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Hull;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.base.visuals.BodyTrackingOffsetRepresentation;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.content.visuals.BorderRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.common.Vec2;

public class ScenarioBorderFactory implements ActorFactory {

   final private float borderThickness = 10.0f;

   private float fieldHeight;
   private float fieldWidth;
   private RigidBodyFactory bodyFactory;

   public ScenarioBorderFactory(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
      bodyFactory = null;
   }

   public void setBodyFactory(RigidBodyFactory factory) {
      bodyFactory = factory;
   }

   public Actor createActor(NewtonianState initialState) {
      RigidBody body = bodyFactory.createStaticBody(initialState);

      for (Primitive primitive : getPrimitives()) {
         Hull hull = new Hull(new Arrow(), primitive, Material.METAL);
         body.addHull(hull, null);
      }

      Representation border = new BorderRepresentation(fieldWidth, fieldHeight);
      Representation rep = new BodyTrackingOffsetRepresentation(border, body);

      return new PassiveObject(body, rep);
   }

   private Collection<Primitive> getPrimitives() {
      Collection<Primitive> primitives = new LinkedList<Primitive>();

      Vec2 border = new Vec2(borderThickness, borderThickness);
      Vec2 innerDiagonal = new Vec2(fieldWidth / 2.0f, fieldHeight / 2.0f);
      Vec2 outerDiagonal = innerDiagonal.add(border);

      Primitive newPrimitive;

      newPrimitive = new Primitive();
      newPrimitive.AddVertex(new Vec2(innerDiagonal.x, innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(innerDiagonal.x, -innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, -innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, innerDiagonal.y));
      primitives.add(newPrimitive);

      newPrimitive = new Primitive();
      newPrimitive.AddVertex(new Vec2(-innerDiagonal.x, innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, -innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-innerDiagonal.x, -innerDiagonal.y));
      primitives.add(newPrimitive);

      newPrimitive = new Primitive();
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, outerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, outerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, innerDiagonal.y));
      primitives.add(newPrimitive);

      newPrimitive = new Primitive();
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, -innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, -innerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(-outerDiagonal.x, -outerDiagonal.y));
      newPrimitive.AddVertex(new Vec2(outerDiagonal.x, -outerDiagonal.y));
      primitives.add(newPrimitive);

      return primitives;
   }

}
