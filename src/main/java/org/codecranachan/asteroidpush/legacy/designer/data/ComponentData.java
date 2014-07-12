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

package org.codecranachan.asteroidpush.legacy.designer.data;

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.legacy.designer.grid.GridVector;

public class ComponentData {
   final ArrayList<EffectorData> effectors;
   final ArrayList<PrimitiveData> primitives;
   final ArrayList<GridVector> weldDirections;

   public ComponentData() {
      this.effectors = new ArrayList<EffectorData>();
      this.primitives = new ArrayList<PrimitiveData>();
      this.weldDirections = new ArrayList<GridVector>();
   }

   public void addEffector(EffectorData data) {
      effectors.add(data);
   }

   public void addPrimitive(PrimitiveData data) {
      primitives.add(data);
   }

   public void addWeldDirection(GridVector direction) {
      weldDirections.add(direction);
   }

   public Collection<EffectorData> getEffectors() {
      return effectors;
   }

   public Collection<PrimitiveData> getPrimitives() {
      return primitives;
   }

   public Collection<GridVector> getWeldDirections() {
      return weldDirections;
   }
}
