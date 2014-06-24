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

package org.codecranachan.asteroidpush.workshop.spaceship;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.assembly.BehaviorFactory;

public class Hardpoint {
   private Collection<OrthogonalCoordinate> hardLinks;
   private Collection<BehaviorFactory> behaviors;

   public Hardpoint() {
      hardLinks = new LinkedList<OrthogonalCoordinate>();
      behaviors = new LinkedList<BehaviorFactory>();
   }

   public void AddHardLink(OrthogonalCoordinate coordinate) {
      hardLinks.add(coordinate);
   }

   public void AddBehaviorFactory(BehaviorFactory factory) {
      behaviors.add(factory);
   }

   public Collection<OrthogonalCoordinate> getHardLinks() {
      return hardLinks;
   }

   public Collection<BehaviorFactory> getBehaviors() {
      return behaviors;
   }

}
