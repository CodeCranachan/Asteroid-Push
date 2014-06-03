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
import org.codecranachan.asteroidpush.designer.data.effectors.EntityEmitterData;
import org.codecranachan.asteroidpush.designer.grid.GridVector;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

public class ProjectileGunFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Projectile Gun");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData gunbase = new PrimitiveData();
      gunbase.setMaterial(Material.METAL);
      Vec2 shape[] = {
            new Vec2(0.3f, -0.5f),
            new Vec2(0.3f, -0.3f),
            new Vec2(0.1f, -0.28f),
            new Vec2(-0.1f, -0.28f),
            new Vec2(-0.3f, -0.3f),
            new Vec2(-0.3f, -0.5f)
      };
      gunbase.setVertices(shape);
      component.addPrimitive(gunbase);

      PrimitiveData gunmuzzle = new PrimitiveData();
      gunmuzzle.setMaterial(Material.METAL);
      Vec2 muzzleshape[] = {
            new Vec2(0.1f, -0.28f),
            new Vec2(0.1f, 0.3f),
            new Vec2(-0.1f, 0.3f),
            new Vec2(-0.1f, -0.28f)
      };
      gunmuzzle.setVertices(muzzleshape);
      component.addPrimitive(gunmuzzle);

      PrimitiveData gundecoright = new PrimitiveData();
      gundecoright.setMaterial(Material.METAL);
      Vec2 decorightshape[] = {
            new Vec2(0.1f, 0.2f),
            new Vec2(0.2f, 0.2f),
            new Vec2(0.2f, 0.45f),
            new Vec2(0.13f, 0.45f),
            new Vec2(0.1f, 0.3f)
      };
      gundecoright.setVertices(decorightshape);
      component.addPrimitive(gundecoright);

      PrimitiveData gundecoleft = new PrimitiveData();
      gundecoleft.setMaterial(Material.METAL);
      Vec2 decoleftshape[] = {
            new Vec2(-0.1f, 0.3f),
            new Vec2(-0.13f, 0.45f),
            new Vec2(-0.2f, 0.45f),
            new Vec2(-0.2f, 0.2f),
            new Vec2(-0.1f, 0.2f)
      };
      gundecoleft.setVertices(decoleftshape);
      component.addPrimitive(gundecoleft);

      EntityEmitterData cannon = new EntityEmitterData();
      cannon.setAnchor(new Vec2(0.0f, 1.0f), MathUtils.HALF_PI);
      cannon.setProjectileVelocity(50.0f);
      component.addEffector(cannon);

      component.addWeldDirection(GridVector.RIGHT);

      return data;
   }

}
