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

package org.codecranachan.asteroidpush.legacy.entities.spaceship;

import org.codecranachan.asteroidpush.legacy.SignalController;
import org.codecranachan.asteroidpush.legacy.entities.spaceship.TorqueFeeder;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jbox2d.dynamics.Body;
import org.junit.Before;
import org.junit.Test;

public class TorqueFeederTest {

   ClassMockery context;
   Body bodyMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      bodyMock = context.mock(Body.class);
   }

   @Test
   public void testUpdate() {
      SignalController testController = new SignalController();
      TorqueFeeder testFeeder = new TorqueFeeder();
      testFeeder.setPropulsee(bodyMock);

      // Body can not be mocked due to excessive use of 'final'
      // so we can only check if the call to update does not crash... 
      testFeeder.update(0, testController);
      testFeeder.update(0, null);
   }
}
