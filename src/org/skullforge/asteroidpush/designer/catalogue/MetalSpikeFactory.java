package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.Material;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

public class MetalSpikeFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Metal Spike");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData block = new PrimitiveData();
      block.setMaterial(Material.METAL);
      Vec2 shape[] = {
            new Vec2(-0.5f, 0.5f),
            new Vec2(-0.5f, -0.5f),
            new Vec2(0.5f, 0.0f)
      };
      block.setVertices(shape);
      component.addPrimitive(block);

      component.addWeldDirection(GridVector.BACK);

      return data;
   }
}
