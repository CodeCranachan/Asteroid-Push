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

package org.skullforge.asteroidpush.board;

import static org.junit.Assert.*;

import org.junit.*;

public class TokenBoardTest {
   private TokenBoard board;

   @Before
   public void set_up() {
      board = new TokenBoard();
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
      board.putToken(new Token(), null);
   }

   @Test
   public void PutTokenOnCenter_BoardNotEmpty() {
      board.putToken(new Token(), new BoardCoordinate());
      assertFalse(board.isEmpty());
   }

   @Test
   public void PickTokenFromEmptyBoard_GetNull() {
      assertNull(board.pickToken(new BoardCoordinate()));
   }

   @Test
   public void PickTokenWhichWasPreviouslyPut_GetThatTokenAndRemoveFromBoard() {
      Token testToken = new Token();
      board.putToken(testToken, new BoardCoordinate());
      assertSame(testToken, board.pickToken(new BoardCoordinate()));
      assertTrue(board.isEmpty());
   }

   @Test
   public void InspectTokenOnEmptyBoard_GetNull() {
      assertNull(board.inspectToken(new BoardCoordinate()));
   }

   @Test
   public void InspectTokenWhichWasPreviouslyPut_GetThatToken() {
      Token testToken = new Token();
      board.putToken(testToken, new BoardCoordinate());
      assertSame(testToken, board.inspectToken(new BoardCoordinate()));
      assertFalse(board.isEmpty());
   }

   @Test
   public void PutTokenOffCenter_GetNullFromCenter() {
      board.putToken(new Token(), new BoardCoordinate(1, 1));
      assertNull(board.inspectToken(new BoardCoordinate()));
   }

   @Test
   public void PutTwoTokensOnBoard_CanInspectBoth() {
      Token firstToken = new Token();
      Token secondToken = new Token();
      BoardCoordinate firstCoordinate = new BoardCoordinate(1, 1);
      BoardCoordinate secondCoordinate = new BoardCoordinate(-1, -1);

      board.putToken(firstToken, firstCoordinate);
      board.putToken(secondToken, secondCoordinate);

      assertSame(firstToken, board.inspectToken(firstCoordinate));
      assertSame(secondToken, board.inspectToken(secondCoordinate));
   }

   @Test(expected = IllegalArgumentException.class)
   public void PutTokenOnOccupiedCoordinate_ThrowIllegalArgumentException() {
      BoardCoordinate location = new BoardCoordinate();
      board.putToken(new Token(), location);
      board.putToken(new Token(), location);
   }

   @Test
   public void PlaceTokenWithUndefinedShape_FormIsSingleSquare() {
      Token token = new Token();
      board.putToken(token, new BoardCoordinate());
      assertNull(board.inspectToken(new BoardCoordinate(1, 0)));
      assertNull(board.inspectToken(new BoardCoordinate(-1, 0)));
      assertNull(board.inspectToken(new BoardCoordinate(0, 1)));
      assertNull(board.inspectToken(new BoardCoordinate(0, -1)));
   }

   @Test
   public void PlaceTokenWithSpecialShape_CanBeFoundOnAllCoordinates() {
      Token token = new Token(new TokenShape("XX"));
      board.putToken(token, new BoardCoordinate());
      assertSame(board.inspectToken(new BoardCoordinate(0, 0)), token);
      assertSame(board.inspectToken(new BoardCoordinate(1, 0)), token);
   }

   @Test(expected = IllegalArgumentException.class)
   public void PlaceOverlappingTokens_ThrowIllegalArgumentException() {
      Token firstToken = new Token(new TokenShape("XX"));
      Token secondToken = new Token(new TokenShape("X", "X"));
      board.putToken(firstToken, new BoardCoordinate(0, 0));
      board.putToken(secondToken, new BoardCoordinate(1, -1));
   }

   @Test
   public void PlaceOverlappingTokens_TokenDoesNotGetPlaced() {
      Token firstToken = new Token(new TokenShape("XX"));
      Token secondToken = new Token(new TokenShape("X", "X"));
      board.putToken(firstToken, new BoardCoordinate(0, 0));
      try {
         board.putToken(secondToken, new BoardCoordinate(1, -1));
      } catch (IllegalArgumentException e) {
         assertNull(board.inspectToken(new BoardCoordinate(1, -1)));
         assertSame(board.inspectToken(new BoardCoordinate(1, 0)), firstToken);
      }
   }

   @Test
   public void RemoveTokenWithSpecialShape_AllCoordinatesAreFreed() {
      Token token = new Token(new TokenShape("XX"));
      board.putToken(token, new BoardCoordinate());
      assertSame(board.pickToken(new BoardCoordinate()), token);
      assertNull(board.inspectToken(new BoardCoordinate(0, 0)));
      assertNull(board.inspectToken(new BoardCoordinate(1, 0)));
   }
}
