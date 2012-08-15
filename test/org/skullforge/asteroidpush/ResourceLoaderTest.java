package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;

public class ResourceLoaderTest {
   Mockery context;
   GameContainer containerMock;
   ResourceLoader testLoader;
   Font fontMock;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      containerMock = context.mock(GameContainer.class);
      fontMock = context.mock(Font.class);
      testLoader = new ResourceLoader();
   }

   @Test
   public void testLoadFont() {
      context.checking(new Expectations() {
         {
            oneOf(containerMock).getDefaultFont();
            will(returnValue(fontMock));
         }
      });
      
      testLoader.setGameContainer(containerMock);
      assertSame(fontMock, testLoader.loadFont("SomeUnavailableFont.ttf", 14));
      
      context.assertIsSatisfied();
   }
}
