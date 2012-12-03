package org.skullforge.asteroidpush.designer.modules;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.assemblies.Material;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;
import org.skullforge.asteroidpush.designer.modules.data.PartData;
import org.skullforge.asteroidpush.designer.modules.data.SubPartData;
import org.skullforge.asteroidpush.doodads.ThrusterFactory;

public class SteamThruster implements ModuleData {

   public SteamThruster() {
      SubPartData subBlock = new SubPartData();
      subBlock.setMaterial(Material.METAL);

      Vec2 shape[] = { 
            new Vec2(0.0f, -0.25f),
            new Vec2(0.25f, -0.25f),
            new Vec2(0.5f, -0.125f),
            new Vec2(0.5f, 0.125f),
            new Vec2(0.25f, 0.25f),
            new Vec2(0.0f, 0.25f) };
      subBlock.setShape(shape);

      PartData nozzle = new PartData();
      nozzle.getSubParts().add(subBlock);
      
      ThrusterFactory factory = new ThrusterFactory();
      nozzle.getEffectorFactories().add(factory);

      parts = new ArrayList<PartData>();
      parts.add(nozzle);
   }
   
   @Override
   public Collection<PartData> getParts() {
      return parts;
   }

   @Override
   public String getName() {
      return "Steam Jet Engine";
   }

   private ArrayList<PartData> parts;
}
