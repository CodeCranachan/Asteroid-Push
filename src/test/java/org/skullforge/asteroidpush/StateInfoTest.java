package org.skullforge.asteroidpush;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateInfoTest {
   @Test
   public void testValueOf() {
      assertEquals(StateInfo.MATCH, StateInfo.valueOf("MATCH"));
      assertEquals(StateInfo.INVALID, StateInfo.valueOf("INVALID"));
   }
}
