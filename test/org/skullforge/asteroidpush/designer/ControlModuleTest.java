package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControlModuleTest {
   ControlModule testModule;

   @Before
   public void setUp() throws Exception {
      testModule = new ControlModule();
   }

   @Test
   public void testGetName() {
      assertEquals("Ticking Engine", testModule.getName());
   }
}
