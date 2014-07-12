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