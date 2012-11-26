package org.skullforge.asteroidpush.designer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.designer.grid.GlueMapTest;
import org.skullforge.asteroidpush.designer.grid.CoordinateTest;

@RunWith(Suite.class)
@SuiteClasses({ ShipDesignTest.class, GlueMapTest.class,
      CoordinateTest.class })
public class DesignerTestSuite {

}
