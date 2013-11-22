package org.skullforge.asteroidpush.designer.grid;

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
