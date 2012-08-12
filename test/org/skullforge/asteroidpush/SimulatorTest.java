package org.skullforge.asteroidpush;

import org.jbox2d.dynamics.World;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.doodads.Doodad;

import static org.junit.Assert.*;

public class SimulatorTest {
   ClassMockery context;
   Doodad abacusMock;
   Doodad bananaMock;
   World worldMock;
   Simulator testSimulator;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(Doodad.class, "Abacus");
      bananaMock = context.mock(Doodad.class, "Banana");
      testSimulator = new Simulator();
   }

   @Test
   public void testAddDoodad() {
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).update(1);
            oneOf(abacusMock).update(2);
            oneOf(abacusMock).update(3);
            oneOf(bananaMock).update(3);
         }
      });
      
      testSimulator.addDoodad(abacusMock);
      testSimulator.stepToFrame(1);
      testSimulator.stepToFrame(2);
      testSimulator.addDoodad(bananaMock);
      testSimulator.stepToFrame(3);
      
      context.assertIsSatisfied();
   }
   
   @Test 
   public void testStepToFrame() {
      context.checking(new Expectations() {
         {
            oneOf(abacusMock).update(1);
            oneOf(abacusMock).update(2);
            oneOf(abacusMock).update(3);
            oneOf(abacusMock).update(4);
            oneOf(abacusMock).update(5);
         }
      });

      assertEquals(0, testSimulator.getCurrentFrameNumber());
      testSimulator.addDoodad(abacusMock);
      testSimulator.stepToFrame(2);
      assertEquals(2, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(5);
      assertEquals(5, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(3);
      assertEquals(5, testSimulator.getCurrentFrameNumber());
      
      context.assertIsSatisfied();
   }
}
