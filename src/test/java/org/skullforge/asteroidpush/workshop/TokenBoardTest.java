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

package org.skullforge.asteroidpush.workshop;

import static org.junit.Assert.*;

import org.junit.*;
import org.skullforge.asteroidpush.workshop.OrthogonalCoordinate;
import org.skullforge.asteroidpush.workshop.Token;
import org.skullforge.asteroidpush.workshop.TokenBoard;
import org.skullforge.asteroidpush.workshop.TokenShape;

public class TokenBoardTest {
   private TokenBoard<Integer> board;

   @Before
   public void set_up() {
      board = new TokenBoard<Integer>();
   }

   @Test
   public void InvokeConstructor_CreateEmpty() {
      assertTrue(board.isEmpty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutNullOnBoard_ThrowIllegalArgumentException() {
      board.putToken(null, null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutSomethingOnNull_ThrowIllegalArgumentException() {
      board.putToken(new Token<Integer>(), null);
   }

   @Test
   public void PutTokenOnCenter_BoardNotEmpty() {
      board.putToken(new Token<Integer>(), new OrthogonalCoordinate());
      assertFalse(board.isEmpty());
   }

   @Test
   public void PickTokenFromEmptyBoard_GetNull() {
      assertNull(board.pickToken(new OrthogonalCoordinate()));
   }

   @Test
   public void PickTokenWhichWasPreviouslyPut_GetThatTokenAndRemoveFromBoard() {
      Token<Integer> testToken = new Token<Integer>();
      board.putToken(testToken, new OrthogonalCoordinate());
      assertSame(testToken, board.pickToken(new OrthogonalCoordinate()));
      assertTrue(board.isEmpty());
   }

   @Test
   public void InspectTokenOnEmptyBoard_GetNull() {
      assertNull(board.inspectToken(new OrthogonalCoordinate()));
   }

   @Test
   public void InspectTokenWhichWasPreviouslyPut_GetThatToken() {
      Token<Integer> testToken = new Token<Integer>();
      board.putToken(testToken, new OrthogonalCoordinate());
      assertSame(testToken, board.inspectToken(new OrthogonalCoordinate()));
      assertFalse(board.isEmpty());
   }

   @Test
   public void PutTokenOffCenter_GetNullFromCenter() {
      board.putToken(new Token<Integer>(), new OrthogonalCoordinate(1, 1));
      assertNull(board.inspectToken(new OrthogonalCoordinate()));
   }

   @Test
   public void PutTwoTokensOnBoard_CanInspectBoth() {
      Token<Integer> firstToken = new Token<Integer>();
      Token<Integer> secondToken = new Token<Integer>();
      OrthogonalCoordinate firstCoordinate = new OrthogonalCoordinate(1, 1);
      OrthogonalCoordinate secondCoordinate = new OrthogonalCoordinate(-1, -1);

      board.putToken(firstToken, firstCoordinate);
      board.putToken(secondToken, secondCoordinate);

      assertSame(firstToken, board.inspectToken(firstCoordinate));
      assertSame(secondToken, board.inspectToken(secondCoordinate));
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutTokenOnOccupiedCoordinate_ThrowIllegalArgumentException() {
      OrthogonalCoordinate location = new OrthogonalCoordinate();
      board.putToken(new Token<Integer>(), location);
      board.putToken(new Token<Integer>(), location);
   }

   @Test
   public void PlaceTokenWithUndefinedShape_FormIsSingleSquare() {
      Token<Integer> token = new Token<Integer>();
      board.putToken(token, new OrthogonalCoordinate());
      assertNull(board.inspectToken(new OrthogonalCoordinate(1, 0)));
      assertNull(board.inspectToken(new OrthogonalCoordinate(-1, 0)));
      assertNull(board.inspectToken(new OrthogonalCoordinate(0, 1)));
      assertNull(board.inspectToken(new OrthogonalCoordinate(0, -1)));
   }

   @Test
   public void PlaceTokenWithSpecialShape_CanBeFoundOnAllCoordinates() {
      Token<Integer> token = new Token<Integer>(new TokenShape("XX"), 0);
      board.putToken(token, new OrthogonalCoordinate());
      assertSame(board.inspectToken(new OrthogonalCoordinate(0, 0)), token);
      assertSame(board.inspectToken(new OrthogonalCoordinate(1, 0)), token);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PlaceOverlappingTokens_ThrowIllegalArgumentException() {
      Token<Integer> firstToken = new Token<Integer>(new TokenShape("XX"), 0);
      Token<Integer> secondToken = new Token<Integer>(new TokenShape("X", "X"),
            0);
      board.putToken(firstToken, new OrthogonalCoordinate(0, 0));
      board.putToken(secondToken, new OrthogonalCoordinate(1, -1));
   }

   @Test
   public void PlaceOverlappingTokens_TokenDoesNotGetPlaced() {
      Token<Integer> firstToken = new Token<Integer>(new TokenShape("XX"), 0);
      Token<Integer> secondToken = new Token<Integer>(new TokenShape("X", "X"),
            0);
      board.putToken(firstToken, new OrthogonalCoordinate(0, 0));
      try {
         board.putToken(secondToken, new OrthogonalCoordinate(1, -1));
      } catch (IllegalArgumentException e) {
         assertNull(board.inspectToken(new OrthogonalCoordinate(1, -1)));
         assertSame(board.inspectToken(new OrthogonalCoordinate(1, 0)),
                    firstToken);
      }
   }

   @Test
   public void RemoveTokenWithSpecialShape_AllCoordinatesAreFreed() {
      Token<Integer> token = new Token<Integer>(new TokenShape("XX"), 0);
      board.putToken(token, new OrthogonalCoordinate());
      assertSame(board.pickToken(new OrthogonalCoordinate()), token);
      assertNull(board.inspectToken(new OrthogonalCoordinate(0, 0)));
      assertNull(board.inspectToken(new OrthogonalCoordinate(1, 0)));
   }
}
