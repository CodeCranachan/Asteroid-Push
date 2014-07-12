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

package org.codecranachan.asteroidpush.legacy;

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.legacy.Timekeeper;
import org.junit.Test;

public class TimekeeperTest {

   @Test
   public void testFrameCalculation() {
      Timekeeper keeper = new Timekeeper(0.15f);
      
      assertEquals(0, keeper.getGameTime());
      keeper.addRealTime(50);
      assertEquals(0, keeper.getGameTime());
      keeper.addRealTime(200);
      assertEquals(1, keeper.getGameTime());
      keeper.addRealTime(250);
      assertEquals(3, keeper.getGameTime());
   }

}
