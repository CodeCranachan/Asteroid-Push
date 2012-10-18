package org.skullforge.asteroidpush;

import java.util.Vector;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.designer.GridCoordinate;
import org.skullforge.asteroidpush.designer.Module;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.testutils.ClassMockery;
import org.skullforge.asteroidpush.ui.DesignerUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DesignerGameStateTest {
   ClassMockery context;
   GameContainer containerMock;
   ResourceLoader loaderMock;
   Graphics graphicsMock;
   StateBasedGame gameMock;
   ShipDesign designMock;
   DesignerUiFactory uiFactoryMock;
   Widget uiMock;
   DesignerGameState testState;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      containerMock = context.mock(GameContainer.class);
      graphicsMock = context.mock(Graphics.class);
      designMock = context.mock(ShipDesign.class);
      uiFactoryMock = context.mock(DesignerUiFactory.class);
      uiMock = context.mock(Widget.class);
      gameMock = context.mock(StateBasedGame.class);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            oneOf(uiFactoryMock).init(designMock);
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
            allowing(designMock).addModule(with(any(GridCoordinate.class)),
                                           with(any(Module.class)));
         }
      });
      testState = new DesignerGameState(designMock, uiFactoryMock);
      testState.init(null, null);
   }

   @Test
   public void testRender() throws SlickException {
      context.checking(new Expectations() {
         {
            oneOf(uiFactoryMock).init(designMock);
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
            allowing(containerMock).getWidth();
            will(returnValue(640));
            allowing(containerMock).getHeight();
            will(returnValue(480));
            allowing(designMock).getModules();
            will(returnValue(new Vector<Module>()));
            allowing(designMock).addModule(with(any(GridCoordinate.class)),
                                           with(any(Module.class)));
            oneOf(uiMock).render(with(graphicsMock), with(any(Rectangle.class)));
         }
      });
      testState = new DesignerGameState(designMock, uiFactoryMock);
      testState.init(null, null);
      testState.render(containerMock, null, graphicsMock);
   }

   @Test
   public void testUpdate() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(designMock).addModule(with(any(GridCoordinate.class)),
                                           with(any(Module.class)));
         }
      });
      testState = new DesignerGameState(designMock, uiFactoryMock);
      testState.update(null, null, 20);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 160);
   }

   @Test
   public void testGetId() {
      context.checking(new Expectations() {
         {
            allowing(designMock).addModule(with(any(GridCoordinate.class)),
                                           with(any(Module.class)));
         }
      });
      testState = new DesignerGameState(designMock, uiFactoryMock);
      assertThat(testState.getID(), is(equalTo(2)));
   }

   @Test
   public void testKeyPressed() throws SlickException {
      context.checking(new Expectations() {
         {
            ignoring(uiFactoryMock);
            allowing(designMock).addModule(with(any(GridCoordinate.class)),
                                           with(any(Module.class)));
            allowing(gameMock).enterState(1);
         }
      });
      testState = new DesignerGameState(designMock, uiFactoryMock);
      testState.init(null, gameMock);
      testState.keyPressed(Input.KEY_SPACE, ' ');

      context.assertIsSatisfied();
   }
}
