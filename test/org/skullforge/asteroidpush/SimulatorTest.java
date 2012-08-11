package org.skullforge.asteroidpush;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimulatorTest {

   Simulator testSimulator;

   @Before
   public void setUp() throws Exception {
      testSimulator = new Simulator();
   }

   @Test
   public void testConstruction() {
      assertNotNull(testSimulator);
   }
}
