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

import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;

public class TokenBoardTest {
   class TokenBoard {
      private Map<BoardCoordinate, Token> tokens;

      public TokenBoard() {
         tokens = new HashMap<BoardCoordinate, Token>();
      }

      public boolean isEmpty() {
         return tokens.isEmpty();
      }

      public void putToken(Token token, BoardCoordinate location) {
         if (token == null)
            throw new IllegalArgumentException("null was passed as token");
         if (location == null)
            throw new IllegalArgumentException("null was passed as location");
         if (tokens.containsKey(location))
            throw new KeyAlreadyExistsException("location already occupied");
         tokens.put(location, token);
      }

      public Token pickToken(BoardCoordinate location) {
         return tokens.remove(location);
      }

      public Token inspectToken(BoardCoordinate location) {
         return tokens.get(location);
      }
   }

   class Token {
      public Token() {

      }

   }

   class BoardCoordinate {
      private int x_;
      private int y_;

      public BoardCoordinate() {
         x_ = 0;
         y_ = 0;
      }

      public BoardCoordinate(int x, int y) {
         x_ = x;
         y_ = y;
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         }
         if (obj.getClass() != BoardCoordinate.class) {
            return false;
         }
         BoardCoordinate other = (BoardCoordinate) obj;
         return (other.x_ == this.x_) && (other.y_ == this.y_);
      }

      public int hashCode() {
         int hash = x_ * (2 ^ 16) + y_;
         return hash;
      }
   }

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

   @Test(expected = KeyAlreadyExistsException.class)
   public void PutTokenOnOccupiedCoordinate_ThrowKeyAlreadyExistsException() {
      BoardCoordinate location = new BoardCoordinate();
      board.putToken(new Token(), location);
      board.putToken(new Token(), location);
   }
}
