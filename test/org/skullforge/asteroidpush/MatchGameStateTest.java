package org.skullforge.asteroidpush;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.skullforge.asteroidpush.ui.Renderer;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchGameStateTest {
   ClassMockery context;
   Scenario scenarioMock;
   Simulator simulatorMock;
   GameContainer containerMock;
   ResourceLoader loaderMock;
   Font fontMock;
   Graphics graphicsMock;
   MatchGameState testState;
   

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
      scenarioMock = context.mock(Scenario.class);
      loaderMock = context.mock(ResourceLoader.class);
      containerMock = context.mock(GameContainer.class);
      graphicsMock = context.mock(Graphics.class);
      fontMock = context.mock(Font.class);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(loaderMock).loadFont(with(any(String.class)), with(any(int.class)));
            will(returnValue(fontMock));
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).initialize(with(aNonNull(Scenario.class)));
         }
      });
      testState = new MatchGameState(simulatorMock, loaderMock);
      testState.setScenario(scenarioMock);
      testState.init(null, null);

      context.assertIsSatisfied();
   }

   @Test
   public void testRender() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(loaderMock).loadFont(with(any(String.class)), with(any(int.class)));
            will(returnValue(fontMock));
            oneOf(simulatorMock).initialize(with(aNonNull(Scenario.class)));
            oneOf(simulatorMock).render(with(aNonNull(Renderer.class)));
            allowing(containerMock).getWidth();
            will(returnValue(640));
            allowing(containerMock).getHeight();
            will(returnValue(480));
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            ignoring(graphicsMock);
            ignoring(fontMock);
         }
      });
      testState = new MatchGameState(simulatorMock, loaderMock);
      testState.setScenario(scenarioMock);
      testState.init(containerMock, null);
      testState.render(containerMock, null, graphicsMock);

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() throws SlickException {
      final Sequence steps = context.sequence("steps");
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(2);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(12);
            inSequence(steps);
         }
      });
      testState = new MatchGameState(simulatorMock, loaderMock);
      testState.update(null, null, 20);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 160);

      context.assertIsSatisfied();
   }

   @Test
   public void testGetId() {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
         }
      });
      testState = new MatchGameState(simulatorMock, loaderMock);
      assertThat(testState.getID(), is(equalTo(1)));

      context.assertIsSatisfied();
   }
}
