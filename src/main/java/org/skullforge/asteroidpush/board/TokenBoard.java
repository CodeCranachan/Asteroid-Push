package org.skullforge.asteroidpush.board;

import java.util.HashMap;
import java.util.Map;

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
         throw new IllegalArgumentException("location already occupied");
      tokens.put(location, token);
   }

   public Token pickToken(BoardCoordinate location) {
      return tokens.remove(location);
   }

   public Token inspectToken(BoardCoordinate location) {
      return tokens.get(location);
   }
}