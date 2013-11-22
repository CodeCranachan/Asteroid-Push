package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimekeeperTest {

   @Test
   public void testFrameCalculation() {
      Timekeeper keeper = new Timekeeper(0.15f);
      
      assertEquals(0, keeper.getGameTime());
      keeper.addRealTime(50);
      assertEquals(0, keeper.getGameTime());
      keeper.addRealTime(200);
      assertEquals(1, keeper.getGameTime());
      keeper.addRealTime(250);
      assertEquals(3, keeper.getGameTime());
   }

}
