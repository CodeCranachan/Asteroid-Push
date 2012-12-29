package org.skullforge.asteroidpush.designer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.designer.catalogue.CatalogueTestSuite;
import org.skullforge.asteroidpush.designer.data.DataTestSuite;
import org.skullforge.asteroidpush.designer.grid.GridTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
      CatalogueTestSuite.class,
      DataTestSuite.class,
      GridTestSuite.class,
      BlueprintTest.class,
      BlueprintManipulator.class
})
public class DesignerTestSuite {

}
