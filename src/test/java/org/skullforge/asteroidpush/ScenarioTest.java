//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
      World testWorld = new World(new Vec2());
      Collection<SimulatorCommand> commands = testScenario
            .getSetupCommands(testWorld);
      assertNotNull(commands);
      assertEquals(6, commands.size());
   }
}
