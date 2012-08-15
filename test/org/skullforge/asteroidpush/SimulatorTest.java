package org.skullforge.asteroidpush;

import java.util.ArrayList;

import org.jbox2d.dynamics.World;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.States;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.ui.Renderer;

import static org.junit.Assert.*;

public class SimulatorTest {
   ClassMockery context;
   Doodad abacusMock;
   Doodad bananaMock;
   Scenario scenarioMock;
   Renderer rendererMock;
   Simulator testSimulator;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      abacusMock = context.mock(Doodad.class, "Abacus");
      bananaMock = context.mock(Doodad.class, "Banana");
      scenarioMock = context.mock(Scenario.class);
      rendererMock = context.mock(Renderer.class);
      testSimulator = new Simulator();
   }

   @Test
   public void testInitialize() {
      ArrayList<Doodad> doodads = new ArrayList<Doodad>();
      doodads.add(abacusMock);
      doodads.add(bananaMock);
      final ArrayList<Doodad> finalDoodads = new ArrayList<Doodad>(doodads);
      context.checking(new Expectations() {
         {
            oneOf(scenarioMock).buildDoodads();
            will(returnValue(finalDoodads));
            oneOf(abacusMock).spawn(with(aNonNull(World.class)));
            oneOf(bananaMock).spawn(with(aNonNull(World.class)));
         }
      });

      testSimulator.initialize(scenarioMock);
      
      context.assertIsSatisfied();
   }

   @Test
   public void testAddDoodad() {
      context.checking(new Expectations() {
         {
            allowing(abacusMock).isSpawned();
            will(returnValue(true));
            allowing(bananaMock).isSpawned();
            will(returnValue(true));
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
      final Sequence update = context.sequence("updateSequence");
      context.checking(new Expectations() {
         {
            allowing(abacusMock).isSpawned();
            will(returnValue(true));
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
      testSimulator.addDoodad(abacusMock);
      testSimulator.stepToFrame(2);
      assertEquals(2, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(5);
      assertEquals(5, testSimulator.getCurrentFrameNumber());
      testSimulator.stepToFrame(3);
      assertEquals(5, testSimulator.getCurrentFrameNumber());

      context.assertIsSatisfied();
   }

   @Test
   public void testSpawningDuringStepping() {
      final States status = context.states("status").startsAs("despawned");
      context.checking(new Expectations() {
         {
            allowing(abacusMock).isSpawned();
            will(returnValue(false));
            when(status.is("despawned"));

            oneOf(abacusMock).spawn(with(aNonNull(World.class)));
            then(status.is("spawned"));

            allowing(abacusMock).isSpawned();
            will(returnValue(true));
            when(status.is("spawned"));

            oneOf(abacusMock).update(2);
            oneOf(abacusMock).update(3);
         }
      });

      testSimulator.addDoodad(abacusMock);
      testSimulator.stepToFrame(3);
      context.assertIsSatisfied();
   }
   
   @Test
   public void testRender() {
      context.checking(new Expectations() {
         {
            allowing(abacusMock).isSpawned();
            will(returnValue(false));
            allowing(bananaMock).isSpawned();
            will(returnValue(true));
            oneOf(bananaMock).render(rendererMock);
         }
      });
      
      testSimulator.addDoodad(abacusMock);
      testSimulator.addDoodad(bananaMock);
      testSimulator.render(rendererMock);
      
      context.assertIsSatisfied();
   }
   
   @Test
   public void testGetTimeStep() {
      assertEquals(0.0165, testSimulator.getTimeStep(), 0.0001);
   }
}
