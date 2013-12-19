package org.skullforge.asteroidpush.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

class TokenShapeTest {
   @Test(expected = IllegalArgumentException.class)
   public void CreateEmptyShape_ThrowInvalidShapeException() {
      new TokenShape("");
   }

   @Test
   public void CompareDefaultShapeWithSingleSqare_ThenAreEqual() {
      assertEquals(new TokenShape("X"), new TokenShape());
   }

   @Test
   public void CompareRectangleShapeWithSingleSquare_ThenNotEqual() {
      assertNotEquals(new TokenShape("X"), new TokenShape("XX"));
   }
}