package org.skullforge.asteroidpush.arena;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class BasicViewportTest {
  Mockery context;
  Graphics graphicsMock;
  Image imageMock;

  @Before
  public void setUp() throws Exception {
    context = new Mockery() {
      {
        setImposteriser(ClassImposteriser.INSTANCE);
      }
    };
    graphicsMock = context.mock(Graphics.class);
    imageMock = context.mock(Image.class);
  }

  @Test
  public void testMappingRectangleToDisplay() {
    Viewport view = new BasicViewport(graphicsMock);
    Rectangle rect = new Rectangle(5.0f, 5.0f, 5.0f, 5.0f);

    context.checking(new Expectations() {
      {
        one(imageMock).getWidth(); will(returnValue(50));
        one(imageMock).getHeight(); will(returnValue(50));
        one(graphicsMock).drawImage(imageMock, 5.0f, 5.0f, 10.0f, 10.0f, 0.0f, 0.0f, 50.0f, 50.0f);
      }
    });
    
    view.showImage(imageMock, rect);

    context.assertIsSatisfied();
  }

}
