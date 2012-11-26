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

   public Coordinate(Coordinate coordinate) {
      this.x = coordinate.getX();
      this.y = coordinate.getY();
   }

   public void set(Coordinate coordinate) {
      this.x = coordinate.x;
      this.y = coordinate.y;
   }

   public Coordinate getFront() {
      return new Coordinate(this.x + 1, this.y);
   }

   public Coordinate getRight() {
      return new Coordinate(this.x, this.y - 1);
   }

   public Coordinate getBack() {
      return new Coordinate(this.x - 1, this.y);
   }

   public Coordinate getLeft() {
      return new Coordinate(this.x, this.y + 1);
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

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != Coordinate.class) {
         return false;
      }
      Coordinate other = (Coordinate) obj;
      return (other.x == this.x) && (other.y == this.y);
   }

   public int hashCode() {
      int hash = x * 32 + y;
      return hash;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(x);
      builder.append("/");
      builder.append(y);
      return builder.toString();
   }

   private int x;
   private int y;
}