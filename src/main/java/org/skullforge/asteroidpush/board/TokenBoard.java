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

import java.util.HashMap;
import java.util.HashSet;
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

      Set<BoardCoordinate> occupiedCoordinates = translateShape(token.getShape(),
                                                                location);
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

   private Set<BoardCoordinate> translateShape(TokenShape shape,
                                               BoardCoordinate offset) {
      Set<BoardCoordinate> cooked = new HashSet<BoardCoordinate>();
      for (BoardCoordinate original : shape.getOccupiedCoordinates()) {
         BoardCoordinate translated = new BoardCoordinate(original);
         translated.move(offset.getX(), offset.getY());
         cooked.add(translated);
      }
      return cooked;
   }
}