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

public class BoardTest {
   private Board<Integer> board;

   @Before
   public void set_up() {
      board = new Board<Integer>();
   }

   @Test
   public void InvokeConstructor_CreateEmpty() {
      assertTrue(board.isEmpty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutNullOnBoard_ThrowIllegalArgumentException() {
      board.place(null, null, 0);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutSomethingOnNull_ThrowIllegalArgumentException() {
      board.place(new Token<Integer>(), null, 0);
   }

   @Test
   public void PutTokenOnCenter_BoardNotEmpty() {
      board.place(new Token<Integer>(), OC(0, 0), 0);
      assertFalse(board.isEmpty());
   }

   @Test
   public void PickTokenFromEmptyBoard_GetNull() {
      assertNull(board.pick(OC(0, 0)));
   }

   @Test
   public void PickTokenWhichWasPreviouslyPut_GetThatTokenAndRemoveFromBoard() {
      Token<Integer> testToken = new Token<Integer>();
      board.place(testToken, OC(0, 0), 0);
      assertSame(testToken, board.pick(OC(0, 0)));
      assertTrue(board.isEmpty());
   }

   @Test
   public void InspectTokenOnEmptyBoard_GetNull() {
      assertNull(board.inspect(OC(0, 0)));
   }

   @Test
   public void InspectTokenWhichWasPreviouslyPut_GetThatToken() {
      Token<Integer> testToken = new Token<Integer>();
      board.place(testToken, OC(0, 0), 0);
      assertSame(testToken, board.inspect(OC(0, 0)));
      assertFalse(board.isEmpty());
   }

   @Test
   public void PutTokenOffCenter_GetNullFromCenter() {
      board.place(new Token<Integer>(), new OrthogonalCoordinate(1, 1), 0);
      assertNull(board.inspect(OC(0, 0)));
   }

   @Test
   public void PutTwoTokensOnBoard_CanInspectBoth() {
      Token<Integer> firstToken = new Token<Integer>();
      Token<Integer> secondToken = new Token<Integer>();
      OrthogonalCoordinate firstCoordinate = new OrthogonalCoordinate(1, 1);
      OrthogonalCoordinate secondCoordinate = new OrthogonalCoordinate(-1, -1);

      board.place(firstToken, firstCoordinate, 0);
      board.place(secondToken, secondCoordinate, 0);

      assertSame(firstToken, board.inspect(firstCoordinate));
      assertSame(secondToken, board.inspect(secondCoordinate));
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutTokenOnOccupiedCoordinate_ThrowIllegalArgumentException() {
      OrthogonalCoordinate location = OC(0, 0);
      board.place(new Token<Integer>(), location, 0);
      board.place(new Token<Integer>(), location, 0);
   }

   @Test
   public void PlaceTokenWithUndefinedShape_FormIsSingleSquare() {
      Token<Integer> token = new Token<Integer>();
      board.place(token, OC(0, 0), 0);
      assertNull(board.inspect(new OrthogonalCoordinate(1, 0)));
      assertNull(board.inspect(new OrthogonalCoordinate(-1, 0)));
      assertNull(board.inspect(new OrthogonalCoordinate(0, 1)));
      assertNull(board.inspect(new OrthogonalCoordinate(0, -1)));
   }

   @Test
   public void PlaceTokenWithSpecialShape_CanBeFoundOnAllCoordinates() {
      Token<Integer> token = new Token<Integer>(new Shape("XX"), 0);
      board.place(token, OC(0, 0), 0);
      assertSame(board.inspect(new OrthogonalCoordinate(0, 0)), token);
      assertSame(board.inspect(new OrthogonalCoordinate(1, 0)), token);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PlaceOverlappingTokens_ThrowIllegalArgumentException() {
      Token<Integer> firstToken = new Token<Integer>(new Shape("XX"), 0);
      Token<Integer> secondToken = new Token<Integer>(new Shape("X", "X"),
            0);
      board.place(firstToken, new OrthogonalCoordinate(0, 0), 0);
      board.place(secondToken, new OrthogonalCoordinate(1, -1), 0);
   }

   @Test
   public void PlaceOverlappingTokens_TokenDoesNotGetPlaced() {
      Token<Integer> firstToken = new Token<Integer>(new Shape("XX"), 0);
      Token<Integer> secondToken = new Token<Integer>(new Shape("X", "X"),
            0);
      board.place(firstToken, new OrthogonalCoordinate(0, 0), 0);
      try {
         board.place(secondToken, new OrthogonalCoordinate(1, -1), 0);
      } catch (IllegalArgumentException e) {
         assertNull(board.inspect(OC(1, -1)));
         assertSame(board.inspect(OC(1, 0)), firstToken);
      }
   }

   @Test
   public void RemoveTokenWithSpecialShape_AllCoordinatesAreFreed() {
      Token<Integer> token = new Token<Integer>(new Shape("XX"), 0);
      board.place(token, OC(0, 0), 0);
      assertSame(board.pick(OC(0, 0)), token);
      assertNull(board.inspect(OC(0, 0)));
      assertNull(board.inspect(OC(1, 0)));
   }

   @Test
   public void PlaceRotatedToken_TokenOccupiesCorrectCoordinates() {
      Token<Integer> token = new Token<Integer>(new Shape("XX"), 0);
      board.place(token, OC(1, 1), 1);
      assertSame(board.inspect(OC(1, 1)), token);
      assertSame(board.inspect(OC(1, 2)), token);
      assertNull(board.inspect(OC(2, 1)));
   }

   private OrthogonalCoordinate OC(int x, int y) {
      return new OrthogonalCoordinate(x, y);
   }
}
