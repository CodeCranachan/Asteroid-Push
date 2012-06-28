package org.skullforge.omegazone.test;

import org.junit.*;
import org.jmock.*;
import org.jmock.lib.legacy.ClassImposteriser;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.skullforge.omegazone.arena.entities.Vessel;

public class VesselTest {
  Mockery context;
  Vessel testVessel;
  Image imageMock;
  Graphics graphicsMock;

  @Before
  public void setUp() throws Exception {
    context = new Mockery() {
      {
        setImposteriser(ClassImposteriser.INSTANCE);
      }
    };
    imageMock = context.mock(Image.class);
    graphicsMock = context.mock(Graphics.class);
    testVessel = new Vessel(imageMock);
  }

  @Test
  public void testRendering() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf (imageMock).setRotation(with(any(float.class)));
        oneOf (graphicsMock).drawImage( with(same(imageMock)), with(any(float.class)), with(any(float.class)) );
      }
    });

    testVessel.render(graphicsMock);

    context.assertIsSatisfied();
  }

  @Test
  public void testUpdating() throws Exception {
    testVessel.update(0);
  }

}
