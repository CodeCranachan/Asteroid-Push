package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.assemblies.Material;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

public class MetalBlockFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Metal Block");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shape[] = {
            new Vec2(0.5f, 0.5f),
            new Vec2(-0.5f, 0.5f),
            new Vec2(-0.5f, -0.5f),
            new Vec2(0.5f, -0.5f)
      };
      block.setShape(shape);
      component.addPrimitive(block);

      component.addWeldDirection(GridVector.FORWARD);
      component.addWeldDirection(GridVector.BACK);
      component.addWeldDirection(GridVector.LEFT);
      component.addWeldDirection(GridVector.RIGHT);

      return data;
   }

}
