package org.skullforge.asteroidpush.designer;

public class GridCoordinate {
   public GridCoordinate() {
      x = 0;
      y = 0;
   }

   public GridCoordinate(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public void set(GridCoordinate coordinate) {
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
