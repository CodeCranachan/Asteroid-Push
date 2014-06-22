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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class Board<TokenData> {
   private Map<OrthogonalCoordinate, Token<TokenData>> content;

   public Board() {
      content = new HashMap<OrthogonalCoordinate, Token<TokenData>>();
   }

   public boolean isEmpty() {
      return content.isEmpty();
   }

   public void place(Token<TokenData> token,
                     OrthogonalCoordinate location,
                     int orientation) {
      if (token == null)
         throw new IllegalArgumentException("null was passed as token");
      if (location == null)
         throw new IllegalArgumentException("null was passed as location");

      // Assign the placement to the token
      Placement placement = new Placement(orientation, location);
      token.setPlacement(placement);

      // Check whether the token overlaps with an other tokens
      for (OrthogonalCoordinate occupied : token.getOccupiedCoordinates()) {
         if (content.containsKey(occupied)) {
            token.setPlacement(null);
            throw new IllegalArgumentException("location already occupied");
         }
      }

      // Update board content with newly placed token
      for (OrthogonalCoordinate occupied : token.getOccupiedCoordinates())
         content.put(occupied, token);
   }

   public Token<TokenData> pick(OrthogonalCoordinate location) {
      Token<TokenData> token = inspect(location);
      if (token != null) {
         content.remove(token);
         for (OrthogonalCoordinate place : token.getOccupiedCoordinates()) {
            content.remove(place);
         }
         token.setPlacement(null);
      }
      return token;
   }

   public Token<TokenData> inspect(OrthogonalCoordinate location) {
      return content.get(location);
   }

   public Collection<Token<TokenData>> getTokens() {
      return content.values();
   }
}