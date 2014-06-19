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

package org.codecranachan.asteroidpush.workshop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TokenBoard<TokenData> {
   private Map<OrthogonalCoordinate, TokenPlacement<TokenData>> locationIndex;
   private Map<Token<TokenData>, TokenPlacement<TokenData>> tokenIndex;

   public TokenBoard() {
      locationIndex = new HashMap<OrthogonalCoordinate, TokenPlacement<TokenData>>();
      tokenIndex = new HashMap<Token<TokenData>, TokenPlacement<TokenData>>();
   }

   public boolean isEmpty() {
      assert (locationIndex.isEmpty() == tokenIndex.isEmpty());
      return tokenIndex.isEmpty();
   }

   public void place(Token<TokenData> token,
                        OrthogonalCoordinate location,
                        int orientation) {
      if (token == null)
         throw new IllegalArgumentException("null was passed as token");
      if (location == null)
         throw new IllegalArgumentException("null was passed as location");

      TokenPlacement<TokenData> placement = new TokenPlacement<TokenData>(
            orientation, location, token);

      Set<OrthogonalCoordinate> occupiedCoordinates = placement
            .getOccupiedCoordinates();

      for (OrthogonalCoordinate occupied : occupiedCoordinates) {
         if (locationIndex.containsKey(occupied))
            throw new IllegalArgumentException("location already occupied");
      }

      for (OrthogonalCoordinate occupied : occupiedCoordinates)
         locationIndex.put(occupied, placement);
      tokenIndex.put(token, placement);
   }

   public TokenPlacement<TokenData> pick(OrthogonalCoordinate location) {
      TokenPlacement<TokenData> placement = inspect(location);
      if (placement != null) {
         tokenIndex.remove(placement.getToken());
         for (OrthogonalCoordinate place : placement.getOccupiedCoordinates()) {
            locationIndex.remove(place);
         }
      }
      return placement;
   }

   public TokenPlacement<TokenData> inspect(OrthogonalCoordinate location) {
      return locationIndex.get(location);
   }

   public Collection<TokenPlacement<TokenData>> getPlacements() {
      return tokenIndex.values();
   }
}