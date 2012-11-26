package org.skullforge.asteroidpush.designer.modules;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.assemblies.Material;
import org.skullforge.asteroidpush.designer.modules.data.ModuleData;
import org.skullforge.asteroidpush.designer.modules.data.PartData;
import org.skullforge.asteroidpush.designer.modules.data.SubPartData;

public class MetalBlock implements ModuleData {

   public MetalBlock() {
      SubPartData subBlock = new SubPartData();
      subBlock.setMaterial(Material.METAL);

      Vec2 shape[] = { 
            new Vec2(0.5f, 0.5f),
            new Vec2(-0.5f, 0.5f),
            new Vec2(-0.5f, -0.5f),
            new Vec2(0.5f, -0.5f) };
      subBlock.setShape(shape);

      PartData block = new PartData();
      block.getSubParts().add(subBlock);

      parts = new ArrayList<PartData>();
      parts.add(block);
   }

   public Collection<PartData> getParts() {
      return parts;
   }

   public String getName() {
      return "Metal Block";
   }

   private ArrayList<PartData> parts;
}
