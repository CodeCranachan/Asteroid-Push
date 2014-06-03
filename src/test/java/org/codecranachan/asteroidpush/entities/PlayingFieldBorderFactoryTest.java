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

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.PassiveObject;
import org.codecranachan.asteroidpush.entities.PlayingFieldBorderFactory;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Test;

public class PlayingFieldBorderFactoryTest {
   @Test
   public void testCreateEntity() {
      World world = new World(new Vec2());
      PlayingFieldBorderFactory testFactory = new PlayingFieldBorderFactory(
            world);
      Entity entity = testFactory.createEntity(new Vec2());
      assertNotNull(entity);
      assertEquals(PassiveObject.class, entity.getClass());
   }
}