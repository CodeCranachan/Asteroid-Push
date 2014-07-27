package org.codecranachan.asteroidpush.legacy;

import org.codecranachan.asteroidpush.legacy.designer.DesignerTestSuite;
import org.codecranachan.asteroidpush.legacy.entities.EntitiesTestSuite;
import org.codecranachan.asteroidpush.legacy.ui.UiTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      DesignerTestSuite.class,
      EntitiesTestSuite.class,
      UiTestSuite.class,

      ScenarioTest.class,
      SimulatorTest.class,
})
public class LegacyTestSuite {

}
