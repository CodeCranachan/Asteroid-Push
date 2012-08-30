package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.junit.*;
import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.AsteroidPush;
import org.skullforge.asteroidpush.GameStateFactory;

public class AsteroidPushTest {
   ClassMockery context;
   GameStateFactory factoryMock;
   ResourceLoader loaderMock;
   GameState arenaStateMock;
   AsteroidPush testApp;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      arenaStateMock = context.mock(GameState.class);
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
            oneOf(factoryMock).createGameState(StateInfo.MATCH, loaderMock);
            oneOf(factoryMock).createGameState(StateInfo.DESIGNER, loaderMock);
            will(returnValue(arenaStateMock));
            oneOf(arenaStateMock).getID();
            will(returnValue(2));
         }
      });

      testApp.initStatesList(null);
      int expectedNumberOfStates = 2;
      assertEquals(expectedNumberOfStates, testApp.getStateCount());

      context.assertIsSatisfied();
   }
}
