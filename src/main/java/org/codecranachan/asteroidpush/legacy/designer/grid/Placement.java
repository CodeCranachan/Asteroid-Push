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

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Placement {
   public Placement() {
      coordinate = new GridVector();
      rotation = new Rotation();
   }
   
   public Placement(int x, int y, Facing facing) {
      this.coordinate = new GridVector(x, y);
      this.rotation = new Rotation(facing);
   }
   
   public void set(Placement placement) {
      this.coordinate.set(placement.getCoordinate());
      this.rotation = placement.rotation;
   }
   
   public GridVector getCoordinate() {
      return coordinate;
   }

   public void setCoordinate(GridVector coordinate) {
      this.coordinate.set(coordinate);
   }

   public Rotation getRotation() {
      return rotation;
   }

   public void setRotation(Rotation rotation) {
      this.rotation = new Rotation(rotation);
   }
   
   public Transform getTransform() {
      Transform transform = new Transform();
      transform.set(new Vec2(coordinate.getX(), coordinate.getY()), rotation.getRadians());
      return transform;
   }

   private GridVector coordinate;
   private Rotation rotation;
}
