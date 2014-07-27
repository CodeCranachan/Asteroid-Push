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

package org.codecranachan.asteroidpush.legacy.ui;

import org.codecranachan.asteroidpush.legacy.ui.SimpleLayout;
import org.codecranachan.asteroidpush.testutils.ClassMockery;
import org.codecranachan.asteroidpush.visuals.widget.Widget;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SimpleLayoutTest {
   Mockery context;
   Graphics graphicsMock;
   Widget backgroundWidgetMock;
   Widget infoWidgetMock;
   Widget controlWidgetMock;
   SimpleLayout testLayout;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      graphicsMock = context.mock(Graphics.class);
      backgroundWidgetMock = context.mock(Widget.class, "background");
      infoWidgetMock = context.mock(Widget.class, "info");
      controlWidgetMock = context.mock(Widget.class, "control");
      testLayout = new SimpleLayout();
   }

   @Test
   public void testDrawMissingWidgets() {
      context.checking(new Expectations() {
         {
            allowing(graphicsMock).drawRoundRect(with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(int.class)));
            allowing(graphicsMock).fillRoundRect(with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(int.class)));
            allowing(graphicsMock).setColor(with(aNonNull(Color.class)));
            allowing(graphicsMock).drawString(with(aNonNull(String.class)),
                                              with(any(float.class)),
                                              with(any(float.class)));
         }
      });
      testLayout.render(graphicsMock);
      context.assertIsSatisfied();
   }

   @Test
   public void testDrawWidgets() {
      context.checking(new Expectations() {
         {
            oneOf(backgroundWidgetMock).render(with(same(graphicsMock)));
            oneOf(infoWidgetMock).render(with(same(graphicsMock)));
            oneOf(controlWidgetMock).render(with(same(graphicsMock)));
         }
      });
      testLayout.setWidget("invalid element name", infoWidgetMock);
      testLayout.setWidget("info", infoWidgetMock);
      testLayout.setWidget("control", controlWidgetMock);
      testLayout.setBackground(backgroundWidgetMock);
      testLayout.render(graphicsMock);
      context.assertIsSatisfied();
   }
}
