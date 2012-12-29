package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class Part {
   final private ComponentData component;
   final private Placement placement;
   private Body body;

   public Part(Placement placement, ComponentData component) {
      this.placement = placement;
      this.component = component;
      this.body = null;
   }

   public ComponentData getComponent() {
      return component;
   }

   public Placement getPlacement() {
      return placement;
   }

   public Body getBody() {
      return body;
   }

   public void setBody(Body body) {
      this.body = body;
   }
}
