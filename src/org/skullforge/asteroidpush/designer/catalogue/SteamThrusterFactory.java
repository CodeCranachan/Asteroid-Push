package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.assemblies.Material;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.data.effectors.ForceFeederData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

public class SteamThrusterFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Steam Jet Engine");

      ComponentData component = new ComponentData();
      data.addComponent(component);

      PrimitiveData nozzle = new PrimitiveData();
      nozzle.setMaterial(Material.METAL);
      Vec2 shape[] = {
            new Vec2(0.0f, -0.25f),
            new Vec2(0.25f, -0.25f),
            new Vec2(0.5f, -0.125f),
            new Vec2(0.5f, 0.125f),
            new Vec2(0.25f, 0.25f),
            new Vec2(0.0f, 0.25f)
      };
      nozzle.setShape(shape);
      component.addPrimitive(nozzle);

      ForceFeederData thruster = new ForceFeederData();
      thruster.setAnchor(new Vec2(0.0f, 0.0f), 0.0f);
      thruster.setMagnitude(50000.0f);
      component.addEffector(thruster);

      component.addWeldDirection(GridVector.FORWARD);

      return data;
   }

}
