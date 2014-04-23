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