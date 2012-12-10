package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;
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
   public void testSetupCommands() {
      World testWorld = new World(new Vec2(), true);
      Collection<SimulatorCommand> commands = testScenario
            .getSetupCommands(testWorld);
      assertNotNull(commands);
      assertEquals(5, commands.size());
   }
}
