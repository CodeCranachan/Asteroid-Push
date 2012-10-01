package org.skullforge.asteroidpush.ui;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;

import org.skullforge.asteroidpush.ClassMockery;
import org.skullforge.asteroidpush.designer.ShipDesign;

public class ShipDesignViewTest {
   ClassMockery context;
   ShipDesignView testDesignView;
   ShipDesign designMock;
   Font fontMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      designMock = context.mock(ShipDesign.class);
      fontMock = context.mock(Font.class);
      testDesignView = new ShipDesignView(designMock, fontMock);
   }

   @Test
   public void testRendering() {
      context.checking(new Expectations() {
         {
            allowing(designMock).getModules();
            will(returnValue(null));
         }
      });

      testDesignView.render(null, null);
   }
}
