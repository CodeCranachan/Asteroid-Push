package org.skullforge.asteroidpush.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.entities.spaceship.SpaceshipTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
      SpaceshipTestSuite.class,
      AsteroidFactoryTest.class,
      PlayingFieldBorderFactoryTest.class
})
public class EntitiesTestSuite {

}
