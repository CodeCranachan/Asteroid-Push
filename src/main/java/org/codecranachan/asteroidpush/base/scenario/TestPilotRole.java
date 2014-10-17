package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.ui.simulation.PilotUi;
import org.codecranachan.asteroidpush.base.ui.simulation.Viewport;

public class TestPilotRole implements Role {

   private ShipPrototypeRule rule;
   private PilotUi ui;

   public TestPilotRole(ShipPrototypeRule rule) {
      assert rule != null;
      this.ui = new PilotUi();
      this.rule = rule;
   }

   public Viewport getInterface() {
      ui.setFocus(rule.getFocus());
      return ui;
   }
}
