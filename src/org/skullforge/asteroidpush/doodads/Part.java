package org.skullforge.asteroidpush.doodads;

import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.data.ComponentData;

public class Part {
   final private ComponentData component;
   final private Placement placement;

   public Part(Placement placement, ComponentData component) {
      this.placement = placement;
      this.component = component;
   }

   public ComponentData getComponent() {
      return component;
   }

   public Placement getPlacement() {
      return placement;
   }
}
