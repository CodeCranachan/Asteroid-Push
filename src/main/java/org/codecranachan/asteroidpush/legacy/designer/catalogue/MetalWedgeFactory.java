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

package org.codecranachan.asteroidpush.legacy.designer.catalogue;

import org.codecranachan.asteroidpush.legacy.designer.data.ComponentData;
import org.codecranachan.asteroidpush.legacy.designer.data.Material;
import org.codecranachan.asteroidpush.legacy.designer.data.ModuleData;
import org.codecranachan.asteroidpush.legacy.designer.data.PrimitiveData;
import org.codecranachan.asteroidpush.legacy.designer.grid.GridVector;
import org.jbox2d.common.Vec2;

public class MetalWedgeFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Metal Wedge");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shape[] = {
            new Vec2(-0.5f, 0.5f),
            new Vec2(-0.5f, -0.5f),
            new Vec2(0.5f, -0.5f)
      };
      block.setVertices(shape);
      component.addPrimitive(block);

      component.addWeldDirection(GridVector.BACK);
      component.addWeldDirection(GridVector.RIGHT);
      return data;
   }
}
