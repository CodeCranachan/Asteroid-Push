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

package org.codecranachan.asteroidpush.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class AssemblyVertex {
   private Collection<OrthogonalCoordinate> rigidConnections;
   private Arrow placement;
   private Collection<AssemblyBinding> bindings;

   public AssemblyVertex() {
      rigidConnections = new LinkedList<OrthogonalCoordinate>();
      bindings = new LinkedList<AssemblyBinding>();
      placement = null;
   }

   public void bindFactory(BehaviorFactory factory, int index) {
      bindings.add(new AssemblyBinding(factory, index));
   }

   public Collection<AssemblyBinding> getBindings() {
      return bindings;
   }

   public Arrow getPlacement() {
      return placement;
   }

   public void setPlacement(Arrow location) {
      this.placement = location;
   }

   public void AddHardLink(OrthogonalCoordinate coordinate) {
      rigidConnections.add(coordinate);
   }

   public Collection<OrthogonalCoordinate> getHardLinks() {
      return rigidConnections;
   }

}
