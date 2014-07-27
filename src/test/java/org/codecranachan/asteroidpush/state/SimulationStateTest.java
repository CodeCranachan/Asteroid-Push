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

package org.codecranachan.asteroidpush.state;

import org.codecranachan.asteroidpush.legacy.Simulator;
import org.codecranachan.asteroidpush.state.SimulationState;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SimulationStateTest {
   ClassMockery context;
   Simulator simulatorMock;
   SimulationState testState;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
   }

   @Test
   public void testGetId() {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
         }
      });
      testState = new SimulationState(null);
      assertThat(testState.getID(), is(equalTo(StateId.SIMULATION)));
      context.assertIsSatisfied();
   }
}
