package org.skullforge.asteroidpush.doodads;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.doodads.spaceship.GlueMapTest;

@RunWith(Suite.class)
@SuiteClasses({
      AsteroidFactoryTest.class,
      DoodadTest.class,
      GlueMapTest.class,
      PlayingFieldBorderFactoryTest.class
})
public class DoodadsTestSuite {

}
