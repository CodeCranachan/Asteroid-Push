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

package org.codecranachan.asteroidpush.legacy.designer.grid;

import java.util.Collection;
import java.util.HashMap;

public class Grid<Type> {

   private HashMap<GridVector, Type> layout;

   public Grid() {
      layout = new HashMap<GridVector, Type>();
   }

   public Type get(GridVector position) {
      return layout.get(position);
   }

   public Collection<Type> getAll() {
      return layout.values();
   }

   public GridVector getMax() {
      if (layout.isEmpty()) {
         return new GridVector();
      }

      GridVector max = null;
      for (GridVector location : layout.keySet()) {
         if (max == null) {
            max = new GridVector(location);
         } else {
            max.setX(Math.max(max.getX(), location.getX()));
            max.setY(Math.max(max.getY(), location.getY()));
         }
      }
      return max;
   }

   public GridVector getMin() {
      if (layout.isEmpty()) {
         return new GridVector();
      }

      GridVector min = null;
      for (GridVector location : layout.keySet()) {
         if (min == null) {
            min = new GridVector(location);
         } else {
            min.setX(Math.min(min.getX(), location.getX()));
            min.setY(Math.min(min.getY(), location.getY()));
         }
      }
      return min;
   }
   
   public void clear() {
      layout.clear();
   }

   public void put(GridVector location, Type module) {
      layout.put(location, module);
   }

   public void remove(GridVector position) {
      layout.remove(position);
   }
}
