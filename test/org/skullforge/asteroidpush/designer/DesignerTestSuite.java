package org.skullforge.asteroidpush.designer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.skullforge.asteroidpush.designer.catalogue.CatalogueTestSuite;
import org.skullforge.asteroidpush.designer.grid.GridTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
      BlueprintTest.class, GridTestSuite.class, CatalogueTestSuite.class
})
public class DesignerTestSuite {

}
