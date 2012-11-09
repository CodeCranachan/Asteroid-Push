package org.skullforge.asteroidpush;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.appearances.AppearancesTestSuite;
import org.skullforge.asteroidpush.designer.DesignerTestSuite;
import org.skullforge.asteroidpush.doodads.DoodadsTestSuite;
import org.skullforge.asteroidpush.assemblies.PartsTestSuite;
import org.skullforge.asteroidpush.ui.UiTestSuite;
import org.skullforge.asteroidpush.utils.UtilsTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ AsteroidPushTest.class, GameStateFactoryTest.class,
      StateInfoTest.class, MatchGameStateTest.class,
      DesignerGameStateTest.class, SimulatorTest.class, ScenarioTest.class,
      TimekeeperTest.class, ResourceLoaderTest.class,
      AppearancesTestSuite.class, DesignerTestSuite.class,
      DoodadsTestSuite.class, PartsTestSuite.class, UiTestSuite.class,
      UtilsTestSuite.class })
public class AllTests {

}
