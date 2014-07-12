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

import org.codecranachan.asteroidpush.legacy.designer.DesignerTestSuite;
import org.codecranachan.asteroidpush.legacy.entities.EntitiesTestSuite;
import org.codecranachan.asteroidpush.legacy.ResourceLoaderTest;
import org.codecranachan.asteroidpush.legacy.MatchGameStateTest;
import org.codecranachan.asteroidpush.legacy.GameStateFactoryTest;
import org.codecranachan.asteroidpush.legacy.DesignerGameStateTest;
import org.codecranachan.asteroidpush.legacy.ScenarioTest;
import org.codecranachan.asteroidpush.legacy.StateInfoTest;
import org.codecranachan.asteroidpush.legacy.SimulatorTest;
import org.codecranachan.asteroidpush.legacy.ui.UiTestSuite;
import org.codecranachan.asteroidpush.simulation.TimekeeperTest;
import org.codecranachan.asteroidpush.utils.UtilsTestSuite;
import org.codecranachan.asteroidpush.workshop.tokenboard.TokenBoardTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      TokenBoardTestSuite.class,
      DesignerTestSuite.class,
      EntitiesTestSuite.class,
      UiTestSuite.class,
      UtilsTestSuite.class,

      AsteroidPushTest.class,
      DesignerGameStateTest.class,
      GameStateFactoryTest.class,
      MatchGameStateTest.class,
      ResourceLoaderTest.class,
      ScenarioTest.class,
      SimulatorTest.class,
      StateInfoTest.class,
      TimekeeperTest.class
})
public class AllTests {

}
