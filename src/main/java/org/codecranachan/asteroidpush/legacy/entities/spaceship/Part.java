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

package org.codecranachan.asteroidpush.legacy.entities.spaceship;

import org.codecranachan.asteroidpush.legacy.designer.data.ComponentData;
import org.codecranachan.asteroidpush.legacy.designer.grid.Placement;
import org.jbox2d.dynamics.Body;

public class Part {
   final private ComponentData component;
   final private Placement placement;
   private Body body;

   public Part(Placement placement, ComponentData component) {
      this.placement = placement;
      this.component = component;
      this.body = null;
   }

   public ComponentData getComponent() {
      return component;
   }

   public Placement getPlacement() {
      return placement;
   }

   public Body getBody() {
      return body;
   }

   public void setBody(Body body) {
      this.body = body;
   }
}
