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

import static org.junit.Assert.*;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.legacy.GameStateFactory;
import org.codecranachan.asteroidpush.legacy.ResourceLoader;
import org.codecranachan.asteroidpush.legacy.Scenario;
import org.codecranachan.asteroidpush.legacy.StateInfo;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Expectations;
import org.junit.*;
import org.newdawn.slick.state.GameState;

public class AsteroidPushTest {
   ClassMockery context;
   GameStateFactory factoryMock;
   ResourceLoader loaderMock;
   GameState matchStateMock;
   GameState designerStateMock;
   AsteroidPush testApp;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      matchStateMock = context.mock(GameState.class, "MatchGameState");
      designerStateMock = context.mock(GameState.class, "DesignerGameState");
      factoryMock = context.mock(GameStateFactory.class);
      loaderMock = context.mock(ResourceLoader.class);
      testApp = new AsteroidPush(factoryMock, loaderMock);
   }

   @Test
   public void testAppName() throws Exception {
      assertEquals("Asteroid Push", testApp.getTitle());
   }

   @Test
   public void testStates() throws Exception {
      context.checking(new Expectations() {
         {
            oneOf(loaderMock).setGameContainer(null);
            oneOf(factoryMock).createGameState(with(StateInfo.MATCH),
                                               with(loaderMock),
                                               with(aNonNull(Scenario.class)));
            will(returnValue(matchStateMock));
            oneOf(factoryMock).createGameState(with(StateInfo.DESIGNER),
                                               with(loaderMock),
                                               with(aNonNull(Scenario.class)));
            will(returnValue(designerStateMock));

            allowing(matchStateMock).getID();
            will(returnValue(1));
            allowing(designerStateMock).getID();
            will(returnValue(2));
         }
      });

      testApp.initStatesList(null);
      int expectedNumberOfStates = 2;
      assertEquals(expectedNumberOfStates, testApp.getStateCount());

      context.assertIsSatisfied();
   }
}
