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

package org.codecranachan.asteroidpush.entities.spaceship;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.codecranachan.asteroidpush.designer.data.ComponentData;
import org.codecranachan.asteroidpush.designer.grid.Facing;
import org.codecranachan.asteroidpush.designer.grid.GridVector;
import org.codecranachan.asteroidpush.designer.grid.Placement;
import org.codecranachan.asteroidpush.entities.spaceship.GlueMap;
import org.codecranachan.asteroidpush.entities.spaceship.Part;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class GlueMapTest {
   Mockery context;
   GlueMap testGlueMap;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      testGlueMap = new GlueMap();
   }

   @Test
   public void testSingleGrouping() {
      Placement firstPlacement = new Placement(0, 0, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 0, Facing.FORWARD);
      Placement thirdPlacement = new Placement(-1, 0, Facing.FORWARD);

      ComponentData weldsInAllDirections = new ComponentData();
      weldsInAllDirections.addWeldDirection(GridVector.FORWARD);
      weldsInAllDirections.addWeldDirection(GridVector.BACK);
      weldsInAllDirections.addWeldDirection(GridVector.LEFT);
      weldsInAllDirections.addWeldDirection(GridVector.RIGHT);

      Part firstPart = new Part(firstPlacement, weldsInAllDirections);
      Part secondPart = new Part(secondPlacement, weldsInAllDirections);
      Part thirdPart = new Part(thirdPlacement, weldsInAllDirections);

      testGlueMap.putPart(firstPart);
      testGlueMap.putPart(secondPart);
      testGlueMap.putPart(thirdPart);

      Collection<Collection<Part>> glueGroups = testGlueMap.getGlueGroups();

      assertEquals(1, glueGroups.size());

      Iterator<Collection<Part>> groupIterator = glueGroups.iterator();
      assertEquals(3, groupIterator.next().size());

      testGlueMap.clear();
   }

   @Test
   public void testMultipleGrouping() {
      Placement firstPlacement = new Placement(0, 1, Facing.FORWARD);
      Placement secondPlacement = new Placement(1, 1, Facing.FORWARD);
      Placement thirdPlacement = new Placement(0, -1, Facing.FORWARD);
      Placement fourthPlacement = new Placement(1, -1, Facing.FORWARD);

      ComponentData weldsInAllDirections = new ComponentData();
      weldsInAllDirections.addWeldDirection(GridVector.FORWARD);
      weldsInAllDirections.addWeldDirection(GridVector.BACK);
      weldsInAllDirections.addWeldDirection(GridVector.LEFT);
      weldsInAllDirections.addWeldDirection(GridVector.RIGHT);

      Part firstPart = new Part(firstPlacement, weldsInAllDirections);
      Part secondPart = new Part(secondPlacement, weldsInAllDirections);
      Part thirdPart = new Part(thirdPlacement, weldsInAllDirections);
      Part fourthPart = new Part(fourthPlacement, weldsInAllDirections);

      testGlueMap.putPart(firstPart);
      testGlueMap.putPart(secondPart);
      testGlueMap.putPart(thirdPart);
      testGlueMap.putPart(fourthPart);

      Collection<Collection<Part>> glueGroups = testGlueMap.getGlueGroups();

      assertEquals(2, glueGroups.size());

      Iterator<Collection<Part>> groupIterator = glueGroups.iterator();
      assertEquals(2, groupIterator.next().size());
      assertEquals(2, groupIterator.next().size());

      testGlueMap.clear();
   }
}
