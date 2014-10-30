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

package org.codecranachan.asteroidpush.base.workshop.tokenboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.codecranachan.asteroidpush.base.visuals.OffsetRepresentation;
import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class Token implements Representable {
   private Placeable placeable;
   private Placement placement;

   private static float TOKEN_SCALE = 0.9f;

   public Token(Placeable placeable) {
      assert placeable != null;

      this.placeable = placeable;
      placement = new Placement(0, new OrthogonalCoordinate());
   }

   public Shape getShape() {
      return placeable.getShape();
   }

   public Placeable getData() {
      return placeable;
   }

   public void rotateClockwise() {
      placement.rotateClockwise();
   }

   public void rotateAnticlockwise() {
      placement.rotateAnticlockwise();
   }

   public void setPlacement(Placement placement) {
      if (placement == null)
         throw new IllegalArgumentException(
               "must not pass null as placement for a Token");
      this.placement = placement;
   }

   public Placement getPlacement() {
      return placement;
   }

   public Set<OrthogonalCoordinate> getOccupiedCoordinates() {
      Set<OrthogonalCoordinate> coordinates = getShape()
            .getOccupiedCoordinates();
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

   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      for (Representation rep : placeable.getRepresentations()) {
         representations.add(new OffsetRepresentation(rep, GeometryConverter
               .convertToArrow(placement), TOKEN_SCALE));
      }
      return representations;
   }
}