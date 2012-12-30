package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.Material;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.data.effectors.EntityEmitterData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

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
      cannon.setAnchor(new Vec2(0.0f, 0.5f), MathUtils.HALF_PI);
      cannon.setProjectileVelocity(100.0f);
      component.addEffector(cannon);

      component.addWeldDirection(GridVector.RIGHT);

      return data;
   }

}
