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

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.Hull;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.RigidBodyFactory;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.content.visuals.PrimitiveRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;
import org.jbox2d.common.Vec2;

public class CannonballFactory implements ActorFactory {

   final private float ballSize = 0.4f;

   private RigidBodyFactory bodyFactory;

   public CannonballFactory() {
      bodyFactory = null;
   }

   public void setBodyFactory(RigidBodyFactory factory) {
      bodyFactory = factory;
   }

   public Actor createActor(Arrow location, Velocity velocity) {
      RigidBody body = bodyFactory.createDynamicBody(location, velocity);

      Primitive primitive = getPrimitive();

      Hull hull = new Hull(new Arrow(), primitive, Material.METAL);
      body.addHull(hull, null);
      Representation rep = new PrimitiveRepresentation(primitive);

      return new PassiveObject(body, rep);
   }

   private Primitive getPrimitive() {
      Primitive primitive = new Primitive();
      Arrow dimensions = new Arrow(new Vec2(), 0f, ballSize / 2f);
      primitive.AddCircle(dimensions, 8);

      return primitive;
   }

}
