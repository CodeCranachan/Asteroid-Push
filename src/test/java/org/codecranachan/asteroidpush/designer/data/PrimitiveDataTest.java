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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.designer.data.Material;
import org.codecranachan.asteroidpush.designer.data.PrimitiveData;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;
import org.junit.Test;

public class PrimitiveDataTest {

   @Test
   public void testFixtureCreation() {
      PrimitiveData testPrimitive = new PrimitiveData();
      testPrimitive.setMaterial(Material.METAL);
      Vec2 testShape[] = {
            new Vec2(-0.5f, 0.0f), new Vec2(0.5f, 0.0f), new Vec2(0.0f, 0.5f)
      };
      testPrimitive.setVertices(testShape);

      Transform testTransform = new Transform();
      testTransform.set(new Vec2(), 0.0f);
      FixtureDef def = testPrimitive.getFixtureDef(testTransform, 1.0f);
      assertThat(def.density, is(Material.METAL.density));
      assertThat(def.restitution, is(Material.METAL.restitution));
      assertThat(def.friction, is(Material.METAL.friction));

      for (Vec2 point : testShape) {
         assertThat(def.shape.testPoint(testTransform, point), is(true));
      }
   }
}
