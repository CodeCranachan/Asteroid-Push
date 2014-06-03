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

package org.codecranachan.asteroidpush.designer.grid;

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.designer.grid.GridVector;
import org.junit.Test;

public class GridVectorTest {

   @Test
   public void testEquals() {
      GridVector testCoordinate = new GridVector();
      GridVector equalCoordinate = new GridVector(0, 0);
      GridVector firstCoordinate = new GridVector(0, 5);
      GridVector secondCoordinate = new GridVector(-2, 4);
      GridVector thirdCoordinate = new GridVector(-56, 0);

      // invalid objects
      assertFalse(testCoordinate.equals(null));
      assertFalse(testCoordinate.equals(new Integer(0)));

      // equality
      assertTrue(testCoordinate.equals(equalCoordinate));
      assertTrue(equalCoordinate.equals(testCoordinate));
      
      // inequality
      assertFalse(testCoordinate.equals(firstCoordinate));
      assertFalse(testCoordinate.equals(secondCoordinate));
      assertFalse(testCoordinate.equals(thirdCoordinate));
      assertFalse(thirdCoordinate.equals(firstCoordinate));
      assertFalse(firstCoordinate.equals(secondCoordinate));
   }
   
   @Test
   public void testAdjacencyGetters() {
      GridVector test = new GridVector(2, -2);
      
      GridVector front = new GridVector(3, -2);
      GridVector back = new GridVector(1, -2);
      GridVector left = new GridVector(2, -1);
      GridVector right = new GridVector(2, -3);
      
      assertEquals(front, test.getFront());
      assertEquals(back, test.getBack());
      assertEquals(left, test.getLeft());
      assertEquals(right, test.getRight());
   }

   @Test
   public void testToString() {
      GridVector first = new GridVector(2, -2);
      GridVector second = new GridVector(5, 0);
      
      assertEquals("2/-2", first.toString());
      assertEquals("5/0", second.toString());
   }
   
   @Test
   public void testSetAndGet() {
      GridVector test = new GridVector();
      GridVector other = new GridVector(22, 34);
      
      assertEquals(0, test.getX());
      assertEquals(0, test.getY());
      test.setX(5);
      assertEquals(5, test.getX());
      assertEquals(0, test.getY());
      test.setY(-4);
      assertEquals(5, test.getX());
      assertEquals(-4, test.getY());
      
      test.set(other);
      assertEquals(other, test);
      assertNotSame(other, test);
      
      GridVector another = new GridVector(other);
      assertEquals(other, another);
      assertNotSame(other, another);
   }
   
}
