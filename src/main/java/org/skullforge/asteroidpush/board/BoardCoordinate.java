package org.skullforge.asteroidpush.board;

class BoardCoordinate {
   private int x_;
   private int y_;

   public BoardCoordinate() {
      x_ = 0;
      y_ = 0;
   }

   public BoardCoordinate(int x, int y) {
      x_ = x;
      y_ = y;
   }

   public int getX() {
      return x_;
   }

   public int getY() {
      return y_;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != BoardCoordinate.class) {
         return false;
      }
      BoardCoordinate other = (BoardCoordinate) obj;
      return (other.x_ == this.x_) && (other.y_ == this.y_);
   }

   public int hashCode() {
      int hash = x_ * (2 ^ 16) + y_;
      return hash;
   }

   public String toString() {
      return String.format("%d/%d", x_, y_);
   }
}