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
import java.util.HashSet;
import java.util.Map;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class Board {
   private Map<OrthogonalCoordinate, Token> content;

   public Board() {
      content = new HashMap<OrthogonalCoordinate, Token>();
   }

   public boolean isEmpty() {
      return content.isEmpty();
   }

   public void place(Token token) {
      if (token == null)
         throw new IllegalArgumentException("null was passed as token");
      if (token.getPlacement() == null)
         throw new IllegalArgumentException(
               "token does not contain a valid placement");

      // Check whether the token overlaps with any other tokens
      for (OrthogonalCoordinate occupied : token.getOccupiedCoordinates()) {
         if (content.containsKey(occupied)) {
            throw new IllegalArgumentException("location already occupied");
         }
      }

      // Update board content with newly placed token
      for (OrthogonalCoordinate occupied : token.getOccupiedCoordinates())
         content.put(occupied, token);
   }

   public Token pick(OrthogonalCoordinate location) {
      Token token = inspect(location);
      if (token != null) {
         content.remove(token);
         for (OrthogonalCoordinate place : token.getOccupiedCoordinates()) {
            content.remove(place);
         }
      }
      return token;
   }

   public Token inspect(OrthogonalCoordinate location) {
      return content.get(location);
   }

   public Collection<Token> getTokens() {
      return new HashSet<Token>(content.values());
   }

   public OrthogonalCoordinate getBottomLeftCorner() {
      if (content.isEmpty()) {
         return new OrthogonalCoordinate(0, 0);
      }

      OrthogonalCoordinate min = null;
      for (OrthogonalCoordinate location : content.keySet()) {
         if (min == null) {
            min = new OrthogonalCoordinate(location);
         } else {
            min.setX(Math.min(min.getX(), location.getX()));
            min.setY(Math.min(min.getY(), location.getY()));
         }
      }
      return min;
   }

   public OrthogonalCoordinate getTopRightCorner() {
      if (content.isEmpty()) {
         return new OrthogonalCoordinate(0, 0);
      }

      OrthogonalCoordinate max = null;
      for (OrthogonalCoordinate location : content.keySet()) {
         if (max == null) {
            max = new OrthogonalCoordinate(location);
         } else {
            max.setX(Math.max(max.getX(), location.getX()));
            max.setY(Math.max(max.getY(), location.getY()));
         }
      }
      return max;
   }
}