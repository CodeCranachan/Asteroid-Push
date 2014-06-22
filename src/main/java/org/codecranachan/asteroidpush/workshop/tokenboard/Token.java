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

package org.codecranachan.asteroidpush.workshop.tokenboard;

import java.util.HashSet;
import java.util.Set;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class Token<Data> {
   private Shape shape;
   private Data data;
   private Placement placement;

   public Token() {
      shape = new Shape("X");
      data = null;
      placement = null;
   }

   public Token(Shape shape, Data data) {
      this.shape = shape;
      this.data = data;
      this.placement = null;
   }

   public Shape getShape() {
      return shape;
   }

   public Data getData() {
      return data;
   }

   public void setPlacement(Placement placement) {
      this.placement = placement;
   }

   public Placement getPlacement() {
      return placement;
   }

   public Set<OrthogonalCoordinate> getOccupiedCoordinates() {
      if (placement == null)
         return new HashSet<OrthogonalCoordinate>();

      Set<OrthogonalCoordinate> coordinates = shape.getOccupiedCoordinates();
      coordinates = rotateShape(coordinates, placement.getOrientation());
      coordinates = translateShape(coordinates, placement.getPivotCoordinate());
      return coordinates;
   }

   private Set<OrthogonalCoordinate> translateShape(Set<OrthogonalCoordinate> shape,
                                                    OrthogonalCoordinate offset) {
      Set<OrthogonalCoordinate> cooked = new HashSet<OrthogonalCoordinate>();
      for (OrthogonalCoordinate original : shape) {
         OrthogonalCoordinate translated = new OrthogonalCoordinate(original);
         translated.move(offset.getX(), offset.getY());
         cooked.add(translated);
      }
      return cooked;
   }

   private Set<OrthogonalCoordinate> rotateShape(Set<OrthogonalCoordinate> shape,
                                                 int quarterTurns) {
      Set<OrthogonalCoordinate> cooked = new HashSet<OrthogonalCoordinate>();
      for (OrthogonalCoordinate original : shape) {
         OrthogonalCoordinate rotated = new OrthogonalCoordinate(original);
         rotated.turn(quarterTurns);
         cooked.add(rotated);
      }
      return cooked;
   }
}