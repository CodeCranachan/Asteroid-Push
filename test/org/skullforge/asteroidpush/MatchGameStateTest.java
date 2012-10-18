package org.skullforge.asteroidpush;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.testutils.ClassMockery;
import org.skullforge.asteroidpush.ui.MatchUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchGameStateTest {
   ClassMockery context;
   Scenario scenarioMock;
   Simulator simulatorMock;
   GameContainer containerMock;
   MatchUiFactory uiFactoryMock;
   Widget uiMock;
   Graphics graphicsMock;
   MatchGameState testState;
   

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
      scenarioMock = context.mock(Scenario.class);
      uiFactoryMock = context.mock(MatchUiFactory.class);
      uiMock = context.mock(Widget.class);
      containerMock = context.mock(GameContainer.class);
      graphicsMock = context.mock(Graphics.class);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).initialize(scenarioMock);
            oneOf(uiFactoryMock).init(simulatorMock);
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
         }
      });
      testState = new MatchGameState(simulatorMock, uiFactoryMock);
      testState.setScenario(scenarioMock);
      testState.init(null, null);

      context.assertIsSatisfied();
   }

   @Test
   public void testRender() throws SlickException {
      final Rectangle expectedCanvasRectangle = new Rectangle(0.0f, 0.0f, 640.0f, 480.0f);
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).initialize(scenarioMock);
            oneOf(uiFactoryMock).init(simulatorMock);
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
            allowing(containerMock).getWidth();
            will(returnValue((int)expectedCanvasRectangle.getWidth()));
            allowing(containerMock).getHeight();
            will(returnValue((int)expectedCanvasRectangle.getHeight()));
            oneOf(uiMock).render(with(graphicsMock), with(any(Rectangle.class)));
         }
      });
      testState = new MatchGameState(simulatorMock, uiFactoryMock);
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
      testState = new MatchGameState(simulatorMock, uiFactoryMock);
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
      testState = new MatchGameState(simulatorMock, uiFactoryMock);
      assertThat(testState.getID(), is(equalTo(1)));

      context.assertIsSatisfied();
   }
}
