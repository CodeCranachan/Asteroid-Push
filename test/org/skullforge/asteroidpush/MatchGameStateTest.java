package org.skullforge.asteroidpush;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchGameStateTest {
   MatchGameState testState;

   @Before
   public void setUp() throws Exception {
      testState = new MatchGameState();
   }

   @Test
   public void testInit() throws SlickException {
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
