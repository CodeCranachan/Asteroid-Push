package org.skullforge.asteroidpush.ui;

import java.util.Vector;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.testutils.ClassMockery;

public class BlueprintDisplayViewTest {
   ClassMockery context;
   BlueprintDisplayView testDesignView;
   Blueprint designMock;
   BlueprintManipulator manipulatorMock;
   Graphics graphicsMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      designMock = context.mock(Blueprint.class);
      graphicsMock = context.mock(Graphics.class);
      manipulatorMock = context.mock(BlueprintManipulator.class);
      testDesignView = new BlueprintDisplayView(designMock, manipulatorMock);
   }

   @Test
   public void testRendering() {
      final Rectangle canvas = new Rectangle(0.0f, 0.0f, 640.0f, 480.0f);
      /*context.checking(new Expectations() {
         {
            ignoring(graphicsMock);
            allowing(designMock).getMin();
            will(returnValue(new GridVector()));
            allowing(designMock).getMax();
            will(returnValue(new GridVector()));
            allowing(designMock).getTokens();
            will(returnValue(new Vector<ModuleToken>()));
            allowing(designMock).getWidth();
            will(returnValue(1));
            allowing(designMock).getHeight();
            will(returnValue(1));
         }
      });

      testDesignView.render(graphicsMock, canvas);

      context.assertIsSatisfied();*/
   }

}
