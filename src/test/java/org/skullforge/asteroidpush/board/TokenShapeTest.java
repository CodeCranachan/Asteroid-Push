package org.skullforge.asteroidpush.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TokenShapeTest {
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

   @Test
   public void CreateDefaultShape_OnlyCenterIsOccupied() {
      Set<BoardCoordinate> expectedCoordinates = new HashSet<BoardCoordinate>();
      expectedCoordinates.add(new BoardCoordinate(0, 0));
      TokenShape testShape = new TokenShape();
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }

   @Test
   public void CreateStarShape_AllAdjacentFieldsAreOccupied() {
      Set<BoardCoordinate> expectedCoordinates = new HashSet<BoardCoordinate>();
      expectedCoordinates.add(new BoardCoordinate(1, 1));
      expectedCoordinates.add(new BoardCoordinate(0, 1));
      expectedCoordinates.add(new BoardCoordinate(2, 1));
      expectedCoordinates.add(new BoardCoordinate(1, 0));
      expectedCoordinates.add(new BoardCoordinate(1, 2));
      TokenShape testShape = new TokenShape(".X.", "XXX", ".X.");
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }

   @Test
   public void CreateIrregularShape_AllCornersAreOccupied() {
      Set<BoardCoordinate> expectedCoordinates = new HashSet<BoardCoordinate>();
      expectedCoordinates.add(new BoardCoordinate(0, 0));
      expectedCoordinates.add(new BoardCoordinate(0, 2));
      expectedCoordinates.add(new BoardCoordinate(2, 0));
      expectedCoordinates.add(new BoardCoordinate(2, 2));
      TokenShape testShape = new TokenShape("X.X", "", "X.X");
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }

   @Test
   public void GetOccupiedCoordinatesWithOffset_ReturnTranslatedCoordinates() {
      BoardCoordinate offset = new BoardCoordinate(1,1);
      Set<BoardCoordinate> expectedCoordinates = new HashSet<BoardCoordinate>();
      expectedCoordinates.add(new BoardCoordinate(1, 1));
      expectedCoordinates.add(new BoardCoordinate(1, 2));
      expectedCoordinates.add(new BoardCoordinate(2, 1));
      expectedCoordinates.add(new BoardCoordinate(2, 2));
      TokenShape testShape = new TokenShape("XX", "XX");
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinatesOffset(offset));
   }
}