package org.codecranachan.asteroidpush.base.scenario;

import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.input.slick2d.Slick2dController;
import org.codecranachan.asteroidpush.base.ui.simulation.PilotUi;
import org.codecranachan.asteroidpush.base.ui.simulation.Viewport;

public class TestPilotRole implements Role {

   private ShipPrototypeRule rule;
   private PilotUi ui;

   public TestPilotRole(ShipPrototypeRule rule, Controller controller) {
      assert rule != null;
      this.ui = createUi(controller);
      this.rule = rule;
   }

   public Viewport getInterface() {
      ui.setFocus(rule.getFocus());
      return ui;
   }

   private PilotUi createUi(Controller controller) {
      assert controller != null;
      return new PilotUi((Slick2dController) controller);
   }
}
