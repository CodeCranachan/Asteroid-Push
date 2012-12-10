package org.skullforge.asteroidpush.doodads.spaceship;

import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.grid.Placement;

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
