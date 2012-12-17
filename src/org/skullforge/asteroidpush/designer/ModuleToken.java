package org.skullforge.asteroidpush.designer;

import java.util.Collection;

import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ModuleToken {
   private ModuleData data;
   private Placement placement;

   public ModuleToken(ModuleData data) {
      this.data = data;
      this.placement = new Placement();
   }

   public ModuleData getData() {
      return data;
   }

   public Placement getPlacement() {
      return placement;
   }

   public void setPlacement(Placement placement) {
      this.placement.set(placement);
   }

   public float getRotation() {
      return placement.getRotation().getRadians();
   }

   public Collection<Shape> getOutline() {
      return data.getOutline();
   }
}
