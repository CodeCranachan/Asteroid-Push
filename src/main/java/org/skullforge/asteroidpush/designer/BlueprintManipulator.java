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

package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.catalogue.MetalSpikeFactory;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.grid.Rotation;

public class BlueprintManipulator {
   Blueprint blueprint;
   Rotation rotation;
   ModuleData data;

   public BlueprintManipulator(Blueprint subject) {
      this.blueprint = subject;
      this.rotation = new Rotation(Facing.FORWARD);
      this.data = MetalSpikeFactory.createData();
   }

   public Rotation getRotation() {
      return this.rotation;
   }

   public ModuleData getSelection() {
      return this.data;
   }

   public void placeModule(GridVector coordinate) {
      Placement placement = new Placement(coordinate.getX(), coordinate.getY(), rotation.getFacing());
      if (blueprint.canAddModule(placement, data)) {
         blueprint.addModule(placement, data);
      }
   }
   
   public void removeModule(GridVector coordinate) {
      blueprint.removeModule(coordinate);
   }

   public void rotateSelectionLeft() {
      rotation.turnAnticlockwise();
   }

   public void rotateSelectionRight() {
      rotation.turnClockwise();
   }

   public void select(ModuleData data) {
      this.data = data;
   }
}
