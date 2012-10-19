package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.junit.*;
import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.AsteroidPush;
import org.skullforge.asteroidpush.GameStateFactory;
import org.skullforge.asteroidpush.testutils.ClassMockery;

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
