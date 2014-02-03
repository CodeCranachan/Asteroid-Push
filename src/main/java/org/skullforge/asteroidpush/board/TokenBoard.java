package org.skullforge.asteroidpush.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class TokenBoard {
   private Map<BoardCoordinate, Token> tokens;
   private Map<Token, Set<BoardCoordinate>> placement;

   public TokenBoard() {
      tokens = new HashMap<BoardCoordinate, Token>();
      placement = new HashMap<Token, Set<BoardCoordinate>>();
   }

   public boolean isEmpty() {
      return tokens.isEmpty();
   }

   public void putToken(Token token, BoardCoordinate location) {
      if (token == null)
         throw new IllegalArgumentException("null was passed as token");
      if (location == null)
         throw new IllegalArgumentException("null was passed as location");

      Set<BoardCoordinate> occupiedCoordinates = token.getShape()
            .getOccupiedCoordinatesOffset(location);
      for (BoardCoordinate occupied : occupiedCoordinates) {
         if (tokens.containsKey(occupied))
            throw new IllegalArgumentException("location already occupied");
      }
      for (BoardCoordinate occupied : occupiedCoordinates)
         tokens.put(occupied, token);
      placement.put(token, occupiedCoordinates);
   }

   public Token pickToken(BoardCoordinate location) {
      Token token = inspectToken(location);
      if (token != null) {
         for (BoardCoordinate place : placement.remove(token)) {
            tokens.remove(place);
         }
      }
      return token;
   }

   public Token inspectToken(BoardCoordinate location) {
      return tokens.get(location);
   }
}