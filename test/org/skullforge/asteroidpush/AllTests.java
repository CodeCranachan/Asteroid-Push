package org.skullforge.asteroidpush;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.skullforge.asteroidpush.appearances.SimpleAppearanceTest;
import org.skullforge.asteroidpush.parts.DebrisTest;
import org.skullforge.asteroidpush.parts.StaticBoxTest;
import org.skullforge.asteroidpush.ui.LabelTest;
import org.skullforge.asteroidpush.ui.SimpleLayoutTest;
import org.skullforge.asteroidpush.designer.ControlModuleTest;
import org.skullforge.asteroidpush.designer.ShipDesignTest;
import org.skullforge.asteroidpush.doodads.DoodadTest;
import org.skullforge.asteroidpush.doodads.PlayingFieldBorderFactoryTest;

@RunWith(Suite.class)
@SuiteClasses({ AsteroidPushTest.class, GameStateFactoryTest.class,
      StateInfoTest.class, StaticBoxTest.class, DoodadTest.class,
      PlayingFieldBorderFactoryTest.class, MatchGameStateTest.class,
      SimulatorTest.class, ScenarioTest.class, LabelTest.class,
      SimpleAppearanceTest.class, TimekeeperTest.class, SimpleLayoutTest.class,
      ResourceLoaderTest.class, DebrisTest.class, ControlModuleTest.class,
      ShipDesignTest.class })
public class AllTests {

}
