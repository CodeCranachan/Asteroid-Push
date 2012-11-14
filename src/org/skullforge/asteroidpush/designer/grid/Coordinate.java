package org.skullforge.asteroidpush.designer.grid;

public class Coordinate {
   public Coordinate() {
      x = 0;
      y = 0;
   }

   public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public void set(Coordinate coordinate) {
      this.x = coordinate.x;
      this.y = coordinate.y;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   private int x;
   private int y;
}
