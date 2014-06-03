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

package org.codecranachan.asteroidpush.designer.data.joints;

import java.util.ArrayList;

import org.codecranachan.asteroidpush.designer.data.JointAnchorData;
import org.codecranachan.asteroidpush.designer.data.JointData;
import org.codecranachan.asteroidpush.entities.spaceship.Part;
import org.jbox2d.dynamics.joints.JointDef;

public class BasicJointData implements JointData {
   private ArrayList<JointAnchorData> anchors;

   public BasicJointData() {
      this.anchors = new ArrayList<JointAnchorData>(2);
      this.anchors.add(null);
      this.anchors.add(null);
   }

   public void setFirst(JointAnchorData data) {
      anchors.set(0, data);
   }

   public void setSecond(JointAnchorData data) {
      anchors.set(1, data);
   }

   public JointAnchorData getFirst() {
      return anchors.get(0);
   }

   public JointAnchorData getSecond() {
      return anchors.get(1);
   }

   public JointDef generateJointDef(Part first, Part second, float moduleSize) {
      return null;
   }

}
