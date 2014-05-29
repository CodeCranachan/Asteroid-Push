package org.skullforge.asteroidpush.board;

import static org.junit.Assert.*;

import org.junit.*;

public class BoardCoordinateTest {
   @Test
   public void InvokeDefaultConstructor_CreateOrigin() {
      BoardCoordinate coordinate = new BoardCoordinate();
      assertEquals(0, coordinate.getX());
      assertEquals(0, coordinate.getY());
   }

   @Test
   public void InvokeConstructor_CreateGivenCoordinate() {
      int expectedX = -5;
      int expectedY = 4;
      BoardCoordinate coordinate = new BoardCoordinate(expectedX, expectedY);
      assertEquals(expectedX, coordinate.getX());
      assertEquals(expectedY, coordinate.getY());
   }

   @Test
   public void InvokeMove_TranslateCoordinate() {
      int initialX = 1;
      int initialY = 5;
      int translationX = -2;
      int translationY = 4;
      BoardCoordinate coordinate = new BoardCoordinate(initialX, initialY);
      coordinate.move(translationX, translationY);
      assertEquals(initialX + translationX, coordinate.getX());
      assertEquals(initialY + translationY, coordinate.getY());
   }
   
   @Test
   public void InvokeTurn_RotateCoordinate() {
      testRotation(6, -2, -5, -2, -6);
      testRotation(6, -2, -4, 6, -2);
      testRotation(6, -2, -3, 2, 6);
      testRotation(6, -2, -2, -6, 2);
      testRotation(6, -2, -1, -2, -6);
      testRotation(6, -2, 0, 6, -2);
      testRotation(6, -2, 1, 2, 6);
      testRotation(6, -2, 2, -6, 2);
      testRotation(6, -2, 3, -2, -6);
      testRotation(6, -2, 4, 6, -2);
      testRotation(6, -2, 5, 2, 6);
   }

   private void testRotation(int initialX,
                             int initialY,
                             int quarterTurns,
                             int expectedX,
                             int expectedY) {
      BoardCoordinate coordinate = new BoardCoordinate(initialX, initialY);
      coordinate.turn(quarterTurns);
      assertEquals(expectedX, coordinate.getX());
      assertEquals(expectedY, coordinate.getY());
   }
}
