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

package org.codecranachan.asteroidpush.legacy.designer;

import org.codecranachan.asteroidpush.legacy.designer.catalogue.CatalogueTestSuite;
import org.codecranachan.asteroidpush.legacy.designer.data.DataTestSuite;
import org.codecranachan.asteroidpush.legacy.designer.grid.GridTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      CatalogueTestSuite.class,
      DataTestSuite.class,
      GridTestSuite.class,
      BlueprintTest.class
})
public class DesignerTestSuite {

}
