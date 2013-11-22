package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.Material;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.data.effectors.TorqueFeederData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

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
