package org.skullforge.asteroidpush;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.designer.DesignerTestSuite;
import org.skullforge.asteroidpush.entities.EntitiesTestSuite;
import org.skullforge.asteroidpush.ui.UiTestSuite;
import org.skullforge.asteroidpush.utils.UtilsTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
      DesignerTestSuite.class,
      EntitiesTestSuite.class,
      UiTestSuite.class,
      UtilsTestSuite.class,

      AsteroidPushTest.class,
      DesignerGameStateTest.class,
      GameStateFactoryTest.class,
      MatchGameStateTest.class,
      ResourceLoaderTest.class,
      ScenarioTest.class,
      SimulatorTest.class,
      StateInfoTest.class,
      TimekeeperTest.class
})
public class AllTests {

}
