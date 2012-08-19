package org.skullforge.asteroidpush.ui;

import org.skullforge.asteroidpush.Simulator;
import org.skullforge.asteroidpush.ui.Widget;

public interface UiFactory {
   Widget createUi(Simulator sim);
}
