package org.skullforge.asteroidpush.arena.entities;

import org.junit.*;
import org.jmock.*;
import org.jmock.lib.legacy.ClassImposteriser;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.skullforge.asteroidpush.arena.Viewport;
import org.skullforge.asteroidpush.arena.entities.Vessel;

public class VesselTest {
  Mockery context;
  Vessel testVessel;
  Image imageMock;
  Viewport viewportMock;

  @Before
  public void setUp() throws Exception {
    context = new Mockery() {
      {
        setImposteriser(ClassImposteriser.INSTANCE);
      }
    };
    imageMock = context.mock(Image.class);
    viewportMock = context.mock(Viewport.class);
    testVessel = new Vessel(imageMock);
  }

  @Test
  public void testRendering() throws Exception {
    final Vector2f expectedOrigin = new Vector2f(2.0f, 2.0f);
    final float expectedRotation = 0.0f;
    final Vector2f expectedSize = new Vector2f(2.0f, 2.0f);
    
    context.checking(new Expectations() {
      {
        oneOf (viewportMock).showImage(imageMock, expectedOrigin, expectedRotation, expectedSize);
      }
    });

    testVessel.render(viewportMock);
    context.assertIsSatisfied();
  }

  @Test
  public void testUpdating() throws Exception {
    testVessel.update(0);
  }

}
