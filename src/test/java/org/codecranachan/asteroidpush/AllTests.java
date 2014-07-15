//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush;

import org.codecranachan.asteroidpush.legacy.LegacyTestSuite;
import org.codecranachan.asteroidpush.simulation.SimulationTestSuite;
import org.codecranachan.asteroidpush.state.StateTestSuite;
import org.codecranachan.asteroidpush.utils.UtilsTestSuite;
import org.codecranachan.asteroidpush.workshop.WorkshopTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      LegacyTestSuite.class,
      SimulationTestSuite.class,
      StateTestSuite.class,
      UtilsTestSuite.class,
      WorkshopTestSuite.class,

      AsteroidPushTest.class,
})
public class AllTests {

}
