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

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class ModuleData {
   private ArrayList<ComponentData> components;
   private ArrayList<JointData> joints;
   private String name;

   public ModuleData(String name) {
      this.components = new ArrayList<ComponentData>();
      this.joints = new ArrayList<JointData>();
      this.name = name;
   }

   public void addComponent(ComponentData data) {
      this.components.add(data);
   }

   public void addJoint(JointData data) {
      this.joints.add(data);
   }

   public Collection<ComponentData> getComponents() {
      return components;
   }

   public Collection<JointData> getJoints() {
      return joints;
   }

   public String getName() {
      return name;
   }

   public Collection<Shape> getOutline() {
      Collection<Shape> outline = new ArrayList<Shape>();

      for (ComponentData componentData : components) {
         for (PrimitiveData primitive : componentData.getPrimitives()) {
            float points[] = primitive.getPointArray();
            Polygon poly = new Polygon(points);
            outline.add(poly);
         }
      }

      return outline;
   }
}
