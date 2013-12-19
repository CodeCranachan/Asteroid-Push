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
}
