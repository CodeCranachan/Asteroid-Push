package org.skullforge.asteroidpush.board;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

class TokenShape {
   private Set<BoardCoordinate> coordinates;

   public TokenShape(String... shape) {
      Vector<String> code = new Vector<String>();
      if (shape.length == 0) {
         code.add("X");
      }

      for (String line : shape) {
         code.add(line);
      }

      coordinates = convertShape(code);
      if (coordinates.isEmpty())
         throw new IllegalArgumentException("invalid shape strings passed");
   }

   public boolean equals(Object obj) {
      if (obj == null)
         return false;
      if (obj.getClass() != TokenShape.class)
         return false;

      TokenShape other = (TokenShape) obj;
      if (other.coordinates.equals(this.coordinates))
         return true;
      else
         return false;
   }

   public Set<BoardCoordinate> getOccupiedCoordinates() {
      return coordinates;
   }

   public Set<BoardCoordinate> getOccupiedCoordinatesOffset(BoardCoordinate offset) {
      Set<BoardCoordinate> cooked = new HashSet<BoardCoordinate>();
      for (BoardCoordinate coordinate : coordinates) {
         cooked.add(new BoardCoordinate(coordinate.getX() + offset.getX(),
               coordinate.getY() + offset.getY()));
      }
      return cooked;
   }

   private Set<BoardCoordinate> convertShape(Vector<String> shape) {
      HashSet<BoardCoordinate> converted = new HashSet<BoardCoordinate>();
      int y = 0;
      for (String line : shape) {
         int x = 0;
         for (char c : line.toCharArray()) {
            if (c == 'X') {
               converted.add(new BoardCoordinate(x, y));
            }
            x++;
         }
         y++;
      }
      return converted;
   }
}