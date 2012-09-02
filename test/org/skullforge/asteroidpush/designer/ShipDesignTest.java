package org.skullforge.asteroidpush.designer;

import static org.junit.Assert.*;
import java.util.Deque;

import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.ShipDesign;

public class ShipDesignTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void testGetModules() {
      ShipDesign testDesign = new ShipDesign();
      
      Deque<Module> modules = testDesign.getModules();
      assertEquals(0, modules.size());
   }
   
}
