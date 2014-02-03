package org.skullforge.asteroidpush.board;

class BoardCoordinate {
   private int x;
   private int y;

   public BoardCoordinate() {
      x = 0;
      y = 0;
   }

   public BoardCoordinate(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != BoardCoordinate.class) {
         return false;
      }
      BoardCoordinate other = (BoardCoordinate) obj;
      return (other.x == this.x) && (other.y == this.y);
   }

   public int hashCode() {
      int hash = x * (2 ^ 16) + y;
      return hash;
   }

   public String toString() {
      return String.format("%d/%d", x, y);
   }
}