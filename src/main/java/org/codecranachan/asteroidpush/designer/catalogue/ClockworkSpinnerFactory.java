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

package org.codecranachan.asteroidpush.designer.catalogue;

import org.codecranachan.asteroidpush.designer.data.ComponentData;
import org.codecranachan.asteroidpush.designer.data.Material;
import org.codecranachan.asteroidpush.designer.data.ModuleData;
import org.codecranachan.asteroidpush.designer.data.PrimitiveData;
import org.codecranachan.asteroidpush.designer.data.effectors.TorqueFeederData;
import org.codecranachan.asteroidpush.designer.grid.GridVector;
import org.jbox2d.common.Vec2;

public class ClockworkSpinnerFactory {
   static public ModuleData createData() {
      ModuleData data = new ModuleData("Clockwork Spinner");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData block;
      block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shapeA[] = {
            new Vec2(-0.5f, -0.5f),
            new Vec2(-0.2f, -0.2f),
            new Vec2(-0.2f, 0.2f),
            new Vec2(-0.5f, 0.5f),
      };
      block.setVertices(shapeA);
      component.addPrimitive(block);

      block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shapeB[] = {
            new Vec2(0.5f, -0.5f),
            new Vec2(0.2f, -0.2f),
            new Vec2(-0.2f, -0.2f),
            new Vec2(-0.5f, -0.5f),
      };
      block.setVertices(shapeB);
      component.addPrimitive(block);

      block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shapeC[] = {
            new Vec2(0.5f, 0.5f),
            new Vec2(0.2f, 0.2f),
            new Vec2(0.2f, -0.2f),
            new Vec2(0.5f, -0.5f),
      };
      block.setVertices(shapeC);
      component.addPrimitive(block);

      block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shapeD[] = {
            new Vec2(-0.5f, 0.5f),
            new Vec2(-0.2f, 0.2f),
            new Vec2(0.2f, 0.2f),
            new Vec2(0.5f, 0.5f),
      };
      block.setVertices(shapeD);
      component.addPrimitive(block);

      component.addWeldDirection(GridVector.FORWARD);
      component.addWeldDirection(GridVector.BACK);
      component.addWeldDirection(GridVector.LEFT);
      component.addWeldDirection(GridVector.RIGHT);

      TorqueFeederData feeder = new TorqueFeederData();
      feeder.setMagnitude(25000.0f);
      component.addEffector(feeder);

      return data;
   }
}
