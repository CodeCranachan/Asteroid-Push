package org.skullforge.asteroidpush.designer.grid;

import java.util.Collection;
import java.util.HashMap;

public class Grid<Type> {

   public Grid() {
      layout = new HashMap<GridVector, Type>();
   }

   public Collection<Type> getAll() {
      return layout.values();
   }

   public void put(GridVector location, Type module) {
      layout.put(location, module);
   }

   public Type get(GridVector position) {
      return layout.get(position);
   }

   public GridVector min() {
      if (layout.isEmpty()) {
         return new GridVector();
      }

      GridVector min = null;
      for (GridVector location : layout.keySet()) {
         if (min == null) {
            min = new GridVector(location);
         } else {
            min.setX(Math.min(location.getX(), location.getX()));
            min.setY(Math.min(location.getY(), location.getY()));
         }
      }
      return min;
   }

   public GridVector max() {
      if (layout.isEmpty()) {
         return new GridVector();
      }

      GridVector max = null;
      for (GridVector location : layout.keySet()) {
         if (max == null) {
            max = new GridVector(location);
         } else {
            max.setX(Math.max(location.getX(), location.getX()));
            max.setY(Math.max(location.getY(), location.getY()));
         }
      }
      return max;
   }

   private HashMap<GridVector, Type> layout;
}
