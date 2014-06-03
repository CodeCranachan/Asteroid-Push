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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class TokenBoard<TokenData> {
   private Map<OrthogonalCoordinate, Token<TokenData>> tokens;
   private Map<Token<TokenData>, Set<OrthogonalCoordinate>> placement;

   public TokenBoard() {
      tokens = new HashMap<OrthogonalCoordinate, Token<TokenData>>();
      placement = new HashMap<Token<TokenData>, Set<OrthogonalCoordinate>>();
   }

   public boolean isEmpty() {
      return tokens.isEmpty();
   }

   public void putToken(Token<TokenData> token, OrthogonalCoordinate location) {
      if (token == null)
         throw new IllegalArgumentException("null was passed as token");
      if (location == null)
         throw new IllegalArgumentException("null was passed as location");

      Set<OrthogonalCoordinate> occupiedCoordinates = translateShape(token.getShape(),
                                                                     location);
      for (OrthogonalCoordinate occupied : occupiedCoordinates) {
         if (tokens.containsKey(occupied))
            throw new IllegalArgumentException("location already occupied");
      }
      for (OrthogonalCoordinate occupied : occupiedCoordinates)
         tokens.put(occupied, token);
      placement.put(token, occupiedCoordinates);
   }

   public Token<TokenData> pickToken(OrthogonalCoordinate location) {
      Token<TokenData> token = inspectToken(location);
      if (token != null) {
         for (OrthogonalCoordinate place : placement.remove(token)) {
            tokens.remove(place);
         }
      }
      return token;
   }

   public Token<TokenData> inspectToken(OrthogonalCoordinate location) {
      return tokens.get(location);
   }

   private Set<OrthogonalCoordinate> translateShape(TokenShape shape,
                                                    OrthogonalCoordinate offset) {
      Set<OrthogonalCoordinate> cooked = new HashSet<OrthogonalCoordinate>();
      for (OrthogonalCoordinate original : shape.getOccupiedCoordinates()) {
         OrthogonalCoordinate translated = new OrthogonalCoordinate(original);
         translated.move(offset.getX(), offset.getY());
         cooked.add(translated);
      }
      return cooked;
   }
}