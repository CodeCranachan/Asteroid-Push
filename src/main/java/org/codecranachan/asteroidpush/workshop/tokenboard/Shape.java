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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class Shape {
   private Set<OrthogonalCoordinate> coordinates;

   public Shape(String... shape) {
      Vector<String> code = new Vector<String>();
      if (shape.length == 0) {
         code.add("X");
      }

      for (String line : shape) {
         code.add(line);
      }

      coordinates = convertShape(code);
      if (coordinates.isEmpty())
         throw new IllegalArgumentException("invalid shape strings passed");
   }

   public boolean equals(Object obj) {
      if (obj == null)
         return false;
      if (obj.getClass() != Shape.class)
         return false;

      Shape other = (Shape) obj;
      if (other.coordinates.equals(this.coordinates))
         return true;
      else
         return false;
   }

   public Set<OrthogonalCoordinate> getOccupiedCoordinates() {
      return coordinates;
   }

   private Set<OrthogonalCoordinate> convertShape(Vector<String> shape) {
      HashSet<OrthogonalCoordinate> converted = new HashSet<OrthogonalCoordinate>();
      int y = 0;
      for (String line : shape) {
         int x = 0;
         for (char c : line.toCharArray()) {
            if (c == 'X') {
               converted.add(new OrthogonalCoordinate(x, y));
            }
            x++;
         }
         y++;
      }
      return converted;
   }
}