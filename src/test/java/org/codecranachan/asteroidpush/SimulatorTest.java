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

package org.codecranachan.asteroidpush;

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.Scenario;
import org.codecranachan.asteroidpush.Simulator;
import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.codecranachan.asteroidpush.ui.Renderer;
import org.jbox2d.dynamics.World;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimulatorTest {
   ClassMockery context;
   Entity abacusMock;
   Entity bananaMock;
   Scenario scenarioMock;
   Renderer rendererMock;
   Simulator testSimulator;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(Entity.class, "Abacus");
      bananaMock = context.mock(Entity.class, "Banana");
      scenarioMock = context.mock(Scenario.class);
      rendererMock = context.mock(Renderer.class);
      testSimulator = new Simulator();
   }

   @Test
   public void freshlyConstructed_FrameNumberIsZero() {
      Simulator testSimulator = new Simulator();
      assertEquals(0, testSimulator.getCurrentFrameNumber());
   }

   @Test
   public void stepToFrameIsCalled_FrameNumberIsSet() {
      assertFrameCounterCorrectlyUpdated(0, 0);
      assertFrameCounterCorrectlyUpdated(0, 5);
      assertFrameCounterCorrectlyUpdated(2, 5);
      assertFrameCounterCorrectlyUpdated(5, 5);
   }

   private void assertFrameCounterCorrectlyUpdated(int initialFrameNumber,
                                                   int finalFrameNumber) {
      Simulator testSimulator = new Simulator();
      testSimulator.stepToFrame(initialFrameNumber);
      testSimulator.stepToFrame(finalFrameNumber);
      assertEquals(finalFrameNumber, testSimulator.getCurrentFrameNumber());
   }
   
   @Test
   public void clearWasCalled_FrameNumberIsReset() {
      Simulator testSimulator = new Simulator();
      testSimulator.stepToFrame(5);
      testSimulator.clear();
      assertEquals(0, testSimulator.getCurrentFrameNumber());
   }


   @Test
   public void testClear() {
      context.checking(new Expectations() {
         {
            exactly(5).of(abacusMock).update(with(any(Integer.class)));
            exactly(5).of(bananaMock).update(with(any(Integer.class)));
            oneOf(abacusMock).destroy();
            oneOf(bananaMock).destroy();
         }
      });
      testSimulator.addEntity(abacusMock);
      testSimulator.addEntity(bananaMock);
      testSimulator.stepToFrame(5);
      testSimulator.clear();

      assertEquals(0, testSimulator.getCurrentFrameNumber());

      context.assertIsSatisfied();
   }

   @Test
   public void testInitialize() {
      final Collection<Entity> commands = new ArrayList<Entity>();
      context.checking(new Expectations() {
         {
            oneOf(scenarioMock).getSetupCommands(with(aNonNull(World.class)));
            will(returnValue(commands));
         }
      });

      testSimulator.initialize(scenarioMock);

      context.assertIsSatisfied();
   }

   @Test
   public void testAddEntity() {
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).update(1);
            oneOf(abacusMock).update(2);
            oneOf(abacusMock).update(3);
            oneOf(bananaMock).update(3);
         }
      });

      testSimulator.addEntity(abacusMock);
      testSimulator.stepToFrame(1);
      testSimulator.stepToFrame(2);
      testSimulator.addEntity(bananaMock);
      testSimulator.stepToFrame(3);

      context.assertIsSatisfied();
   }

   @Test
   public void testStepToFrame() {
      final Sequence update = context.sequence("updateSequence");
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).update(1);
            inSequence(update);
            oneOf(abacusMock).update(2);
            inSequence(update);
            oneOf(abacusMock).update(3);
            inSequence(update);
            oneOf(abacusMock).update(4);
            inSequence(update);
            oneOf(abacusMock).update(5);
            inSequence(update);
         }
      });

      assertEquals(0, testSimulator.getCurrentFrameNumber());
      testSimulator.addEntity(abacusMock);
      testSimulator.stepToFrame(2);
      assertEquals(2, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(5);
      assertEquals(5, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(3);
      assertEquals(5, testSimulator.getCurrentFrameNumber());

      context.assertIsSatisfied();
   }

   @Test
   public void testRender() {
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).render(rendererMock);
            oneOf(bananaMock).render(rendererMock);
         }
      });

      testSimulator.addEntity(abacusMock);
      testSimulator.addEntity(bananaMock);
      testSimulator.render(rendererMock);

      context.assertIsSatisfied();
   }

   @Test
   public void testGetTimeStep() {
      assertEquals(0.0165, testSimulator.getTimeStep(), 0.0001);
   }
}
