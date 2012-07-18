package org.skullforge.asteroidpush.arena;

import org.jmock.*;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class BasicArenaTest {
  Mockery context;
  BasicArena testArena;
  GameContainer containerMock;
  Graphics graphicsMock;
  Viewport viewportMock;
  EntityFactory factoryMock;
  Entity objectMock1;
  Entity objectMock2;

  @Before
  public void setUp() throws Exception {
    context = new Mockery() {
      {
        setImposteriser(ClassImposteriser.INSTANCE);
      }
    };
    factoryMock = context.mock(EntityFactory.class, "factory");
    testArena = new BasicArena(factoryMock);
    containerMock = context.mock(GameContainer.class, "container");
    graphicsMock = context.mock(Graphics.class, "graphics");
    viewportMock = context.mock(Viewport.class, "viewport");
    objectMock1 = context.mock(Entity.class, "object1");
    objectMock2 = context.mock(Entity.class, "object2");
  }
  
  @Test
  public void testInit() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf (factoryMock).createVessel(); will(returnValue(objectMock1));
      }
    });

    testArena.init();
    
    context.assertIsSatisfied();
  }
  
  @Test
  public void testDelegatingRenderOperation() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf (viewportMock).setGraphics(graphicsMock);
        oneOf (objectMock1).render(viewportMock);
        oneOf (objectMock2).render(viewportMock);
      }
    });
    
    testArena.addObject(objectMock1);
    testArena.addObject(objectMock2);
    testArena.setViewport(viewportMock);
    testArena.render(containerMock, graphicsMock);

    context.assertIsSatisfied();
  }
  
  @Test
  public void testMissingViewport() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf (graphicsMock).drawString("NO VISUAL SIGNAL CONNECTED", 25.0f, 25.0f);
      }
    });
    
    testArena.render(containerMock, graphicsMock);
  }
  
  @Test
  public void testDelegatingUpdateOperation() throws Exception {
    final int expectedDelta = 25;
    
    context.checking(new Expectations() {{
      oneOf (objectMock1).update(expectedDelta);
      oneOf (objectMock2).update(expectedDelta);
    }});
    
    testArena.addObject(objectMock1);
    testArena.addObject(objectMock2);
    testArena.update(expectedDelta);
    
    context.assertIsSatisfied();
  }
}
