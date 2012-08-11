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
   GameState arenaStateMock;
   AsteroidPush testApp;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      arenaStateMock = context.mock(GameState.class);
      factoryMock = context.mock(GameStateFactory.class);
      testApp = new AsteroidPush(factoryMock);
   }

   @Test
   public void testAppName() throws Exception {
      assertEquals("Asteroid Push", testApp.getTitle());
   }

   @Test
   public void testStates() throws Exception {
      context.checking(new Expectations() {
         {
            oneOf(factoryMock).createGameState(StateInfo.ARENA);
            will(returnValue(arenaStateMock));
            oneOf(arenaStateMock).getID();
            will(returnValue(1));
         }
      });

      testApp.initStatesList(null);
      int expectedNumberOfStates = 1;
      assertEquals(expectedNumberOfStates, testApp.getStateCount());

      context.assertIsSatisfied();
   }
}
