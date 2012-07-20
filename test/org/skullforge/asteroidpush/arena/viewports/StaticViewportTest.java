package org.skullforge.asteroidpush.arena.viewports;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.skullforge.asteroidpush.arena.Viewport;
import org.skullforge.asteroidpush.arena.viewports.StaticViewport;

public class StaticViewportTest {
   Mockery context;
   Graphics graphicsMock;
   GameContainer containerMock;
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
      containerMock = context.mock(GameContainer.class);
   }

   @Test
   public void testMappingRectangleToDisplay() {
      final Vec2 origin = new Vec2(0.0f, 0.0f);
      final float expectedRotation = 90.0f;
      final Vec2 size = new Vec2(1.0f, 1.0f);
      final float rotation = MathUtils.DEG2RAD * expectedRotation;

      context.checking(new Expectations() {
         {
            allowing(imageMock).getWidth();
            will(returnValue(50));
            allowing(imageMock).getHeight();
            will(returnValue(50));
            oneOf(imageMock).setCenterOfRotation(size.x / 2.0f, size.x / 2.0f);
            oneOf(imageMock).setRotation(expectedRotation);
            oneOf(graphicsMock).scale(10.0f, 10.0f);
            oneOf(graphicsMock).setBackground(with(any(Color.class)));
            oneOf(graphicsMock).drawImage(imageMock,
                                          -0.5f,
                                          -0.5f,
                                          0.5f,
                                          0.5f,
                                          0.0f,
                                          0.0f,
                                          50.0f,
                                          50.0f);
         }
      });

      Viewport view = new StaticViewport();
      view.setGraphics(containerMock, graphicsMock);
      view.showImage(imageMock, origin, rotation, size);

      context.assertIsSatisfied();
   }
}
