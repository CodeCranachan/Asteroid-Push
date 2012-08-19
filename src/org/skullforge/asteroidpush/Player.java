package org.skullforge.asteroidpush;

import org.skullforge.asteroidpush.ui.UiFactory;
import org.skullforge.asteroidpush.ui.Widget;

public class Player {
   public Player () {
      ui = null;
   }
   
   public String getName() {
      return "Anonymous";
   }
   
   public void init(Simulator sim, UiFactory uiFactory) {
      ui = uiFactory.createUi(sim);
   }
   
   public Widget getUi() {
      return ui;
   }
   
   Widget ui;
}
