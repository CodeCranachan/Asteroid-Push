package org.skullforge.asteroidpush.designer.grid;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordinateTest {

   @Test
   public void testEquals() {
      Coordinate testCoordinate = new Coordinate();
      Coordinate equalCoordinate = new Coordinate(0, 0);
      Coordinate firstCoordinate = new Coordinate(0, 5);
      Coordinate secondCoordinate = new Coordinate(-2, 4);
      Coordinate thirdCoordinate = new Coordinate(-56, 0);

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
      Coordinate test = new Coordinate(2, -2);
      
      Coordinate front = new Coordinate(3, -2);
      Coordinate back = new Coordinate(1, -2);
      Coordinate left = new Coordinate(2, -1);
      Coordinate right = new Coordinate(2, -3);
      
      assertEquals(front, test.getFront());
      assertEquals(back, test.getBack());
      assertEquals(left, test.getLeft());
      assertEquals(right, test.getRight());
   }

   @Test
   public void testToString() {
      Coordinate first = new Coordinate(2, -2);
      Coordinate second = new Coordinate(5, 0);
      
      assertEquals("2/-2", first.toString());
      assertEquals("5/0", second.toString());
   }
   
   @Test
   public void testSetAndGet() {
      Coordinate test = new Coordinate();
      Coordinate other = new Coordinate(22, 34);
      
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
      
      Coordinate another = new Coordinate(other);
      assertEquals(other, another);
      assertNotSame(other, another);
   }
   
}
