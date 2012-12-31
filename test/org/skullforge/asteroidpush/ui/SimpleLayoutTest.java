package org.skullforge.asteroidpush.ui;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.skullforge.asteroidpush.testutils.ClassMockery;
import org.skullforge.asteroidpush.ui.Widget;

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
