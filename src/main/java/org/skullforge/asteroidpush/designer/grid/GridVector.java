package org.skullforge.asteroidpush.designer.grid;

public class GridVector {
   public static GridVector BACK = new GridVector(-1, 0);
   public static GridVector FORWARD = new GridVector(1, 0);
   public static GridVector LEFT = new GridVector(0, 1);
   public static GridVector RIGHT = new GridVector(0, -1);

   private int x;
   private int y;

   public GridVector() {
      x = 0;
      y = 0;
   }

   public GridVector(GridVector coordinate) {
      this.x = coordinate.getX();
      this.y = coordinate.getY();
   }

   public GridVector(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public GridVector add(GridVector other) {
      return new GridVector(this.x + other.x, this.y + other.y);
   }

   public GridVector sub(GridVector other) {
      return new GridVector(this.x - other.x, this.y - other.y);
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != GridVector.class) {
         return false;
      }
      GridVector other = (GridVector) obj;
      return (other.x == this.x) && (other.y == this.y);
   }

   public GridVector getBack() {
      return add(BACK);
   }

   public GridVector getFront() {
      return add(FORWARD);
   }

   public GridVector getLeft() {
      return add(LEFT);
   }

   public GridVector getRight() {
      return add(RIGHT);
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public int hashCode() {
      int hash = x * 32 + y;
      return hash;
   }

   public void set(GridVector coordinate) {
      this.x = coordinate.x;
      this.y = coordinate.y;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(x);
      builder.append("/");
      builder.append(y);
      return builder.toString();
   }

   public GridVector turn(Rotation rotation) {
      switch (rotation.getQuarterTurns()) {
      case 1:
         return new GridVector(-y, x);
      case 2:
         return new GridVector(-x, -y);
      case 3:
         return new GridVector(y, -x);
      case 0:
      default:
         return new GridVector(x, y);
      }
   }
}