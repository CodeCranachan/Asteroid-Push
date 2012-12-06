package org.skullforge.asteroidpush.ui;

import java.util.Vector;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class ShipDesignViewTest {
   ClassMockery context;
   ShipDesignView testDesignView;
   Blueprint designMock;
   Graphics graphicsMock;
   Font fontMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      designMock = context.mock(Blueprint.class);
      fontMock = context.mock(Font.class);
      graphicsMock = context.mock(Graphics.class);
      testDesignView = new ShipDesignView(designMock, fontMock);
   }

   @Test
   public void testRendering() {
      final Rectangle canvas = new Rectangle(0.0f, 0.0f, 640.0f, 480.0f);
      context.checking(new Expectations() {
         {
            allowing(designMock).getTokens();
            will(returnValue(new Vector<ModuleToken>()));
         }
      });

      testDesignView.render(graphicsMock, canvas);
   }
}
