package org.skullforge.asteroidpush;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DesignerGameStateTest {
   ClassMockery context;
   Scenario scenarioMock;
   Simulator simulatorMock;
   GameContainer containerMock;
   ResourceLoader loaderMock;
   Font fontMock;
   Graphics graphicsMock;
   StateBasedGame gameMock;
   DesignerGameState testState;


   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
      scenarioMock = context.mock(Scenario.class);
      loaderMock = context.mock(ResourceLoader.class);
      containerMock = context.mock(GameContainer.class);
      graphicsMock = context.mock(Graphics.class);
      fontMock = context.mock(Font.class);
      gameMock = context.mock(StateBasedGame.class);
   }

   @Test
   public void testInit() throws SlickException {
      testState = new DesignerGameState();
      testState.init(null, null);
   }

   @Test
   public void testRender() throws SlickException {
      testState = new DesignerGameState();
      testState.init(null, null);
      testState.render(containerMock, null, graphicsMock);
   }

   @Test
   public void testUpdate() throws SlickException {
      testState = new DesignerGameState();
      testState.update(null, null, 20);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 160);
   }

   @Test
   public void testGetId() {
      testState = new DesignerGameState();
      assertThat(testState.getID(), is(equalTo(2)));
   }

   @Test
   public void testKeyPressed() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(gameMock).enterState(1);
         }
      });
      testState = new DesignerGameState();
      testState.init(null, gameMock);
      testState.keyPressed(Input.KEY_SPACE, ' ');

      context.assertIsSatisfied();
   }
}
