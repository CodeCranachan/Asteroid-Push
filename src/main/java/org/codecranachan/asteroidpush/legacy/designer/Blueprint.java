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

package org.codecranachan.asteroidpush.legacy.designer;

import java.util.Collection;

import org.codecranachan.asteroidpush.legacy.designer.data.ModuleData;
import org.codecranachan.asteroidpush.legacy.designer.grid.Grid;
import org.codecranachan.asteroidpush.legacy.designer.grid.GridVector;
import org.codecranachan.asteroidpush.legacy.designer.grid.Placement;

public class Blueprint {
   private Grid<ModuleToken> grid;

   public Blueprint() {
      grid = new Grid<ModuleToken>();
   }

   public void addModule(Placement placement, ModuleData data) {
      if (canAddModule(placement, data)) {
         ModuleToken token = new ModuleToken(data);
         token.setPlacement(placement);
         grid.put(placement.getCoordinate(), token);
      }
   }

   public boolean canAddModule(Placement placement, ModuleData data) {
      if (grid.get(placement.getCoordinate()) != null) {
         return false;
      } else {
         return true;
      }
   }
   
   public void removeModule(GridVector coordinate) {
      grid.remove(coordinate);
   }

   public void clear() {
      grid.clear();
   }

   public GridVector getMin() {
      return grid.getMin();
   }

   public GridVector getMax() {
      return grid.getMax();
   }

   public int getWidth() {
      return getMax().getX() - getMin().getX() + 1;
   }

   public int getHeight() {
      return getMax().getY() - getMin().getY() + 1;
   }

   public Collection<ModuleToken> getTokens() {
      return grid.getAll();
   }
}
