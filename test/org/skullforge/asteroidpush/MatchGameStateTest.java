package org.skullforge.asteroidpush;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchGameStateTest {
   ClassMockery context;
   Simulator simulatorMock;
   MatchGameState testState;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
      testState = new MatchGameState(simulatorMock);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            oneOf(simulatorMock).initialize(with(aNonNull(Scenario.class)));
         }
      });
      testState.init(null, null);
   }

   @Test
   public void testRender() throws SlickException {
      testState.render(null, null, null);
   }

   @Test
   public void testUpdate() throws SlickException {
      testState.update(null, null, 0);
   }

   @Test
   public void testGetId() {
      assertThat(testState.getID(), is(equalTo(2)));
   }
}
