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

import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.designer.data.JointData;

public class Link {
   private JointData data;
   private Part first;
   private Part second;

   public Link(JointData data) {
      this.data = data;
      this.first = null;
      this.second = null;
   }

   public void match(Part part) {
      if (part.getComponent() == data.getFirst().component) {
         first = part;
      }
      if (part.getComponent() == data.getSecond().component) {
         second = part;
      }
   }

   public JointDef getJointDef(float standardModuleSize) {
      if (first == null || second == null) {
         return null;
      }

      if (first.getBody() == second.getBody()) {
         return null;
      }

      return data.generateJointDef(first, second, standardModuleSize);
   }
}
