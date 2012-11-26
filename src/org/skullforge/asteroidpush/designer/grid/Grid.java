package org.skullforge.asteroidpush.designer.grid;

import java.util.Collection;
import java.util.HashMap;

public class Grid<Type> {

   public Grid() {
      layout = new HashMap<Coordinate, Type>();
   }

   public Collection<Type> getModules() {
      return layout.values();
   }

   public void put(Coordinate location, Type module) {
      layout.put(location, module);
   }

   public Type get(Coordinate position) {
      return layout.get(position);
   }

   public Coordinate min() {
      if (layout.isEmpty()) {
         return new Coordinate();
      }

      Coordinate min = null;
      for (Coordinate location : layout.keySet()) {
         if (min == null) {
            min = new Coordinate(location);
         } else {
            min.setX(Math.min(location.getX(), location.getX()));
            min.setY(Math.min(location.getY(), location.getY()));
         }
      }
      return min;
   }

   public Coordinate max() {
      if (layout.isEmpty()) {
         return new Coordinate();
      }

      Coordinate max = null;
      for (Coordinate location : layout.keySet()) {
         if (max == null) {
            max = new Coordinate(location);
         } else {
            max.setX(Math.max(location.getX(), location.getX()));
            max.setY(Math.max(location.getY(), location.getY()));
         }
      }
      return max;
   }

   private HashMap<Coordinate, Type> layout;
}
