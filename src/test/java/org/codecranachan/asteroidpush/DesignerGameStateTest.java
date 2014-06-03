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

import java.util.Vector;

import org.codecranachan.asteroidpush.DesignerGameState;
import org.codecranachan.asteroidpush.Player;
import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.Scenario;
import org.codecranachan.asteroidpush.designer.Blueprint;
import org.codecranachan.asteroidpush.designer.BlueprintManipulator;
import org.codecranachan.asteroidpush.designer.ModuleToken;
import org.codecranachan.asteroidpush.designer.data.ModuleData;
import org.codecranachan.asteroidpush.designer.grid.Placement;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.codecranachan.asteroidpush.ui.DesignerUiFactory;
import org.codecranachan.asteroidpush.ui.Widget;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DesignerGameStateTest {
   ClassMockery context;
   GameContainer containerMock;
   ResourceLoader loaderMock;
   Graphics graphicsMock;
   StateBasedGame gameMock;
   Blueprint designMock;
   Player playerMock;
   DesignerUiFactory uiFactoryMock;
   Widget uiMock;
   Scenario scenarioMock;
   DesignerGameState testState;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      containerMock = context.mock(GameContainer.class);
      graphicsMock = context.mock(Graphics.class);
      playerMock = context.mock(Player.class);
      designMock = context.mock(Blueprint.class);
      uiFactoryMock = context.mock(DesignerUiFactory.class);
      uiMock = context.mock(Widget.class);
      gameMock = context.mock(StateBasedGame.class);
      scenarioMock = context.mock(Scenario.class);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(playerMock).getShipDesign();
            will(returnValue(designMock));
            oneOf(uiFactoryMock).init(with(same(playerMock)), with(any(BlueprintManipulator.class)));
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
            allowing(scenarioMock).getLocalPlayer();
            will(returnValue(playerMock));
         }
      });
      testState = new DesignerGameState(uiFactoryMock);
      testState.setScenario(scenarioMock);
      testState.init(null, null);
   }

   @Test
   public void testRender() throws SlickException {
      context.checking(new Expectations() {
         {
            oneOf(uiFactoryMock).init(with(same(playerMock)), with(any(BlueprintManipulator.class)));
            oneOf(uiFactoryMock).createUi();
            will(returnValue(uiMock));
            allowing(containerMock).getWidth();
            will(returnValue(640));
            allowing(containerMock).getHeight();
            will(returnValue(480));
            allowing(scenarioMock).getLocalPlayer();
            will(returnValue(playerMock));
            allowing(playerMock).getShipDesign();
            will(returnValue(designMock));
            allowing(designMock).getTokens();
            will(returnValue(new Vector<ModuleToken>()));
            allowing(designMock).addModule(with(any(Placement.class)),
                                           with(any(ModuleData.class)));
            oneOf(uiMock).resize(with(any(Rectangle.class)));
            oneOf(uiMock)
                  .render(with(graphicsMock));
         }
      });
      testState = new DesignerGameState(uiFactoryMock);
      testState.setScenario(scenarioMock);
      testState.init(null, null);
      testState.render(containerMock, null, graphicsMock);
   }

   @Test
   public void testUpdate() throws SlickException {
      testState = new DesignerGameState(uiFactoryMock);
      testState.update(null, null, 0);
   }

   @Test
   public void testGetId() {
      context.checking(new Expectations() {
         {
            allowing(designMock).addModule(with(any(Placement.class)),
                                           with(any(ModuleData.class)));
         }
      });
      testState = new DesignerGameState(uiFactoryMock);
      assertThat(testState.getID(), is(equalTo(2)));
   }

   @Test
   public void testKeyPressed() throws SlickException {
      context.checking(new Expectations() {
         {
            ignoring(uiFactoryMock);
            allowing(designMock).addModule(with(any(Placement.class)),
                                           with(any(ModuleData.class)));
            allowing(gameMock).enterState(1);
            allowing(scenarioMock).getLocalPlayer();
            will(returnValue(playerMock));
            allowing(playerMock).getShipDesign();
            will(returnValue(designMock));
         }
      });
      testState = new DesignerGameState(uiFactoryMock);
      testState.setScenario(scenarioMock);
      testState.init(null, gameMock);
      testState.keyPressed(Input.KEY_SPACE, ' ');

      context.assertIsSatisfied();
   }
}
