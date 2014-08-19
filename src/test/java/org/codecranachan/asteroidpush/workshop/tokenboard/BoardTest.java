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

package org.codecranachan.asteroidpush.workshop.tokenboard;

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.tokenboard.Board;
import org.junit.*;

class TestPlaceable implements Placeable {
   private Shape shape;

   public TestPlaceable(Shape shape) {
      assert shape != null;
      this.shape = shape;
   }

   public Shape getShape() {
      return shape;
   }
}

public class BoardTest {
   private Board board;

   @Before
   public void set_up() {
      board = new Board();
   }

   @Test
   public void InvokeConstructor_CreateEmpty() {
      assertTrue(board.isEmpty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutNullOnBoard_ThrowIllegalArgumentException() {
      board.place(null);
   }

   @Test
   public void PutTokenOnCenter_BoardNotEmpty() {
      board.place(T0(OC(0, 0), 0));
      assertFalse(board.isEmpty());
   }

   @Test
   public void PickTokenFromEmptyBoard_GetNull() {
      assertNull(board.pick(OC(0, 0)));
   }

   @Test
   public void PickTokenWhichWasPreviouslyPut_GetThatTokenAndRemoveFromBoard() {
      Token testToken = T0(OC(0, 0), 0);
      board.place(testToken);
      assertSame(testToken, board.pick(OC(0, 0)));
      assertTrue(board.isEmpty());
   }

   @Test
   public void InspectTokenOnEmptyBoard_GetNull() {
      assertNull(board.inspect(OC(0, 0)));
   }

   @Test
   public void InspectTokenWhichWasPreviouslyPut_GetThatToken() {
      Token testToken = T0(OC(0, 0), 0);
      board.place(testToken);
      assertSame(testToken, board.inspect(OC(0, 0)));
      assertFalse(board.isEmpty());
   }

   @Test
   public void PutTokenOffCenter_GetNullFromCenter() {
      Token testToken = T0(OC(1, 1), 0);
      board.place(testToken);
      assertNull(board.inspect(OC(0, 0)));
   }

   @Test
   public void PutTwoTokensOnBoard_CanInspectBoth() {
      OrthogonalCoordinate firstCoordinate = OC(1, 1);
      OrthogonalCoordinate secondCoordinate = OC(-1, -1);
      Token firstToken = T0(firstCoordinate, 0);
      Token secondToken = T0(secondCoordinate, 0);
      board.place(firstToken);
      board.place(secondToken);

      assertSame(firstToken, board.inspect(firstCoordinate));
      assertSame(secondToken, board.inspect(secondCoordinate));
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutTokenOnOccupiedCoordinate_ThrowIllegalArgumentException() {
      board.place(T0(OC(1, 1), 0));
      board.place(T0(OC(1, 1), 0));
   }

   @Test
   public void PlaceTokenWithBasicShape_CanOnlyBeFoundOnPivotCoordinate() {
      Token token = T0(OC(0, 0), 0);
      board.place(token);
      assertNull(board.inspect(OC(1, 0)));
      assertNull(board.inspect(OC(-1, 0)));
      assertNull(board.inspect(OC(0, 1)));
      assertNull(board.inspect(OC(0, -1)));
   }

   @Test
   public void PlaceTokenWithSpecialShape_CanBeFoundOnAllCoordinates() {
      Token token = T(new Shape("XX"), OC(0, 0), 0);
      board.place(token);
      assertSame(board.inspect(OC(0, 0)), token);
      assertSame(board.inspect(OC(1, 0)), token);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PlaceOverlappingTokens_ThrowIllegalArgumentException() {
      Token firstToken = T(new Shape("XX"), OC(0, 0), 0);
      Token secondToken = T(new Shape("X", "X"), OC(1, -1), 0);
      board.place(firstToken);
      board.place(secondToken);
   }

   @Test
   public void PlaceOverlappingTokens_TokenDoesNotGetPlaced() {
      Token firstToken = T(new Shape("XX"), OC(0, 0), 0);
      Token secondToken = T(new Shape("X", "X"), OC(1, -1), 0);
      board.place(firstToken);
      try {
         board.place(secondToken);
      } catch (IllegalArgumentException e) {
         assertNull(board.inspect(OC(1, -1)));
         assertSame(board.inspect(OC(1, 0)), firstToken);
      }
   }

   @Test
   public void RemoveTokenWithSpecialShape_AllCoordinatesAreFreed() {
      Token token = T(new Shape("XX"), OC(0, 0), 0);
      board.place(token);
      assertSame(board.pick(OC(0, 0)), token);
      assertNull(board.inspect(OC(0, 0)));
      assertNull(board.inspect(OC(1, 0)));
   }

   @Test
   public void PlaceRotatedToken_TokenOccupiesCorrectCoordinates() {
      Token token = T(new Shape("XX"), OC(1, 1), 1);
      board.place(token);
      assertSame(board.inspect(OC(1, 1)), token);
      assertSame(board.inspect(OC(1, 2)), token);
      assertNull(board.inspect(OC(2, 1)));
   }

   private OrthogonalCoordinate OC(int x, int y) {
      return new OrthogonalCoordinate(x, y);
   }

   private Token T0(OrthogonalCoordinate coord, int rotation) {
      Token testToken = new Token(null);
      testToken.setPlacement(new Placement(rotation, coord));
      return testToken;
   }

   private Token T(Shape shape, OrthogonalCoordinate coord, int rotation) {
      Token testToken = new Token(new TestPlaceable(shape));
      testToken.setPlacement(new Placement(rotation, coord));
      return testToken;
   }
}
