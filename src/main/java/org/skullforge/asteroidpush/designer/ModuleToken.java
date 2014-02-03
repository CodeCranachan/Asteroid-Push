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

import java.util.Collection;

import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.grid.Rotation;

public class ModuleToken {
   private ModuleData data;
   private Placement placement;

   public ModuleToken(ModuleData data) {
      this.data = data;
      this.placement = new Placement();
   }

   public ModuleData getData() {
      return data;
   }

   public Placement getPlacement() {
      return placement;
   }

   public void setPlacement(Placement placement) {
      this.placement.set(placement);
   }

   public float getRotation() {
      return placement.getRotation().getRadians();
   }
   
   public void setRotation(Rotation rotation) {
      placement.setRotation(rotation);
   }

   public Collection<Shape> getOutline() {
      return data.getOutline();
   }
}
