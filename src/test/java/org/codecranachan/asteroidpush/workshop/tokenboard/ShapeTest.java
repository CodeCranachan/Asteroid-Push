package org.codecranachan.asteroidpush.workshop.tokenboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.tokenboard.Shape;
import org.junit.Test;

public class ShapeTest {
   @Test(expected = IllegalArgumentException.class)
   public void CreateEmptyShape_ThrowInvalidShapeException() {
      new Shape("");
   }

   @Test
   public void CompareDefaultShapeWithSingleSqare_ThenAreEqual() {
      assertEquals(new Shape("X"), new Shape());
   }

   @Test
   public void CompareRectangleShapeWithSingleSquare_ThenNotEqual() {
      assertNotEquals(new Shape("X"), new Shape("XX"));
   }

   @Test
   public void CreateDefaultShape_OnlyCenterIsOccupied() {
      Set<OrthogonalCoordinate> expectedCoordinates = new HashSet<OrthogonalCoordinate>();
      expectedCoordinates.add(new OrthogonalCoordinate(0, 0));
      Shape testShape = new Shape();
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }

   @Test
   public void CreateStarShape_AllAdjacentFieldsAreOccupied() {
      Set<OrthogonalCoordinate> expectedCoordinates = new HashSet<OrthogonalCoordinate>();
      expectedCoordinates.add(new OrthogonalCoordinate(1, 1));
      expectedCoordinates.add(new OrthogonalCoordinate(0, 1));
      expectedCoordinates.add(new OrthogonalCoordinate(2, 1));
      expectedCoordinates.add(new OrthogonalCoordinate(1, 0));
      expectedCoordinates.add(new OrthogonalCoordinate(1, 2));
      Shape testShape = new Shape(".X.", "XXX", ".X.");
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }

   @Test
   public void CreateIrregularShape_AllCornersAreOccupied() {
      Set<OrthogonalCoordinate> expectedCoordinates = new HashSet<OrthogonalCoordinate>();
      expectedCoordinates.add(new OrthogonalCoordinate(0, 0));
      expectedCoordinates.add(new OrthogonalCoordinate(0, 2));
      expectedCoordinates.add(new OrthogonalCoordinate(2, 0));
      expectedCoordinates.add(new OrthogonalCoordinate(2, 2));
      Shape testShape = new Shape("X.X", "", "X.X");
      assertEquals(expectedCoordinates, testShape.getOccupiedCoordinates());
   }
}