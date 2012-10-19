package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.GameStateFactory;
import org.skullforge.asteroidpush.testutils.ClassMockery;

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
