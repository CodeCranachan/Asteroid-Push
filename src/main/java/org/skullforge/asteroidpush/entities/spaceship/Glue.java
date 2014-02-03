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

package org.skullforge.asteroidpush.entities.spaceship;

import java.util.ArrayList;
import java.util.Collection;


public class Glue {

   public static void link(Glue first, Glue second) {
      first.links.add(second);
      second.links.add(first);
   }

   private Part part;
   private boolean isVisited;
   private ArrayList<Glue> links;

   public Glue() {
      this.links = new ArrayList<Glue>();
      this.part = null;
      this.isVisited = false;
   }

   public Glue(Part part) {
      this.links = new ArrayList<Glue>();
      this.part = part;
   }

   public void clear() {
      links.clear();
      part = null;
   }

   public Collection<Glue> getLinks() {
      return links;
   }

   public Part getPart() {
      return part;
   }

   public boolean isVisited() {
      return isVisited;
   }

   public void resetVisited() {
      isVisited = false;
   }

   public void setVisited() {
      isVisited = true;
   }
}
