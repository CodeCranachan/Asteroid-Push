package org.skullforge.asteroidpush;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.skullforge.asteroidpush.appearances.SimpleAppearanceTest;
import org.skullforge.asteroidpush.parts.DebrisTest;
import org.skullforge.asteroidpush.parts.StaticBoxTest;
import org.skullforge.asteroidpush.ui.UiTests;
import org.skullforge.asteroidpush.designer.ControlModuleTest;
import org.skullforge.asteroidpush.designer.ShipDesignTest;
import org.skullforge.asteroidpush.doodads.DoodadTest;
import org.skullforge.asteroidpush.doodads.PlayingFieldBorderFactoryTest;

@RunWith(Suite.class)
@SuiteClasses({ AsteroidPushTest.class, GameStateFactoryTest.class,
      StateInfoTest.class, StaticBoxTest.class, DoodadTest.class,
      PlayingFieldBorderFactoryTest.class, MatchGameStateTest.class,
      DesignerGameStateTest.class, SimulatorTest.class, ScenarioTest.class,
      SimpleAppearanceTest.class, TimekeeperTest.class,
      ResourceLoaderTest.class, DebrisTest.class,
      ControlModuleTest.class, ShipDesignTest.class, 
      UiTests.class
      })
public class AllTests {

}
