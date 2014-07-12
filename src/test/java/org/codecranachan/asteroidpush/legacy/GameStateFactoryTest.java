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

import org.codecranachan.asteroidpush.legacy.GameStateFactory;
import org.codecranachan.asteroidpush.legacy.MatchGameState;
import org.codecranachan.asteroidpush.legacy.ResourceLoader;
import org.codecranachan.asteroidpush.legacy.Scenario;
import org.codecranachan.asteroidpush.legacy.StateInfo;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.state.GameState;

public class GameStateFactoryTest {
   Mockery context;
   ResourceLoader loaderMock;
   Font fontMock;
   Scenario scenarioMock;
   GameStateFactory testFactory;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      loaderMock = context.mock(ResourceLoader.class);
      fontMock = context.mock(Font.class);
      scenarioMock = context.mock(Scenario.class);
      testFactory = new GameStateFactory();
   }

   @Test
   public void testInvalidStateCreation() throws Exception {
      GameState testState;
      testState = testFactory.createGameState(null, loaderMock, scenarioMock);
      assertNull(testState);
      testState = testFactory.createGameState(StateInfo.INVALID,
                                              loaderMock,
                                              scenarioMock);
      assertNull(testState);
   }

   @Test
   public void testMatchStateCreation() throws Exception {
      context.checking(new Expectations() {
         {
            allowing(loaderMock).loadFont(with(any(String.class)),
                                          with(any(int.class)));
            will(returnValue(fontMock));
         }
      });

      GameState testState = testFactory.createGameState(StateInfo.MATCH,
                                                        loaderMock,
                                                        scenarioMock);
      assertEquals(testState.getClass(), MatchGameState.class);

      context.assertIsSatisfied();
   }

}
