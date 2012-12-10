package org.skullforge.asteroidpush;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.dynamics.World;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.doodads.Entity;
import org.skullforge.asteroidpush.testutils.ClassMockery;
import org.skullforge.asteroidpush.ui.Renderer;

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
