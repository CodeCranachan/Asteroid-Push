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

package org.codecranachan.asteroidpush.base.simulation;

import static org.junit.Assert.assertEquals;

import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.PhysicsEngine;
import org.codecranachan.asteroidpush.base.simulation.Simulation;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;

public class SimulationTest {
   ClassMockery context;
   Actor abacusMock;
   Actor bananaMock;
   PhysicsEngine engineMock;
   Simulation testSimulation;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(Actor.class, "Abacus");
      bananaMock = context.mock(Actor.class, "Banana");
      engineMock = context.mock(PhysicsEngine.class, "PhysicsEngine");
      testSimulation = new Simulation(engineMock);
   }

   @Test
   public void freshlyConstructed_FrameNumberIsZero() {
      assertEquals(0, testSimulation.getCurrentFrameNumber());
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
      context.checking(new Expectations() {
         {
            ignoring(engineMock).stepWorld();
         }
      });
      Simulation mySimulation = new Simulation(engineMock);
      mySimulation.stepToFrame(initialFrameNumber);
      mySimulation.stepToFrame(finalFrameNumber);
      assertEquals(finalFrameNumber, mySimulation.getCurrentFrameNumber());
   }

   @Test
   public void clearWasCalled_FrameNumberIsReset() {
      context.checking(new Expectations() {
         {
            ignoring(engineMock).stepWorld();
         }
      });
      testSimulation.stepToFrame(5);
      testSimulation.clear();
      assertEquals(0, testSimulation.getCurrentFrameNumber());
   }

   @Test
   public void testClear() {
      context.checking(new Expectations() {
         {
            exactly(5).of(engineMock).stepWorld();
            exactly(5).of(abacusMock).update(with(any(Integer.class)));
            exactly(5).of(bananaMock).update(with(any(Integer.class)));
            oneOf(abacusMock).destroy();
            oneOf(bananaMock).destroy();
         }
      });
      testSimulation.addActor(abacusMock);
      testSimulation.addActor(bananaMock);
      testSimulation.stepToFrame(5);
      testSimulation.clear();

      assertEquals(0, testSimulation.getCurrentFrameNumber());

      context.assertIsSatisfied();
   }

   @Test
   public void testAddEntity() {
      context.checking(new Expectations() {
         {
            exactly(3).of(engineMock).stepWorld();
            oneOf(abacusMock).update(1);
            oneOf(abacusMock).update(2);
            oneOf(abacusMock).update(3);
            oneOf(bananaMock).update(3);
         }
      });

      testSimulation.addActor(abacusMock);
      testSimulation.stepToFrame(1);
      testSimulation.stepToFrame(2);
      testSimulation.addActor(bananaMock);
      testSimulation.stepToFrame(3);

      context.assertIsSatisfied();
   }

   @Test
   public void testStepToFrame() {
      final Sequence update = context.sequence("updateSequence");
      context.checking(new Expectations() {
         {
            exactly(5).of(engineMock).stepWorld();
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

      assertEquals(0, testSimulation.getCurrentFrameNumber());
      testSimulation.addActor(abacusMock);
      testSimulation.stepToFrame(2);
      assertEquals(2, testSimulation.getCurrentFrameNumber());
      testSimulation.stepToFrame(5);
      assertEquals(5, testSimulation.getCurrentFrameNumber());
      testSimulation.stepToFrame(3);
      assertEquals(5, testSimulation.getCurrentFrameNumber());

      context.assertIsSatisfied();
   }

}
