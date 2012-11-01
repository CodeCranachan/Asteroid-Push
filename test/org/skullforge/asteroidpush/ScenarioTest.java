package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class ScenarioTest {
   ClassMockery context;
   Player playerMock;
   Scenario testScenario;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      playerMock = context.mock(Player.class);
      testScenario = new Scenario(playerMock);
   }

   @Test
   public void testBuildDoodads() {
      ArrayList<Doodad> doodads = testScenario.buildDoodads();
      assertNotNull(doodads);
      assertEquals(6, doodads.size());
   }
}
