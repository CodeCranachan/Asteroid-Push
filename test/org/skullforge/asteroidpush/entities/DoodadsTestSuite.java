package org.skullforge.asteroidpush.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.entities.spaceship.GlueMapTest;

@RunWith(Suite.class)
@SuiteClasses({
      AsteroidFactoryTest.class,
      GlueMapTest.class,
      PlayingFieldBorderFactoryTest.class
})
public class DoodadsTestSuite {

}
