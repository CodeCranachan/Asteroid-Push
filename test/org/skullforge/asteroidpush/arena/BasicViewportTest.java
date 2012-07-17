package org.skullforge.asteroidpush.arena;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

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
    final Vector2f origin = new Vector2f(0.0f, 0.0f);
    final float rotation = 0.0f;
    final Vector2f size = new Vector2f(1.0f, 1.0f);

    context.checking(new Expectations() {
      {
        allowing(imageMock).getWidth();
        will(returnValue(50));
        allowing(imageMock).getHeight();
        will(returnValue(50));
        oneOf (imageMock).setCenterOfRotation(size.x/2.0f, size.x/2.0f);
        oneOf (imageMock).setRotation(rotation);
        oneOf (graphicsMock).scale(25.0f, 25.0f);
        oneOf (graphicsMock).drawImage(imageMock,
                                    0.0f,
                                    0.0f,
                                    1.0f,
                                    1.0f,
                                    0.0f,
                                    0.0f,
                                    50.0f,
                                    50.0f);
      }
    });

    Viewport view = new BasicViewport(graphicsMock);
    view.showImage(imageMock, origin, rotation, size);

    context.assertIsSatisfied();
  }
}
