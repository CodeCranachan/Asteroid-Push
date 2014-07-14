package org.codecranachan.asteroidpush.workshop;

import org.codecranachan.asteroidpush.workshop.tokenboard.TokenBoardTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      OrthogonalCoordinateTest.class,
      TokenBoardTestSuite.class
})
public class WorkshopTestSuite {

}
