package org.skullforge.asteroidpush.arena;

import org.jmock.*;
import org.junit.*;

public class BasicArenaTest {
  Mockery context;
  BasicArena testArena;
  EntityFactory factoryMock;
  Entity objectMock1;
  Entity objectMock2;

  @Before
  public void setUp() throws Exception {
    context = new Mockery();
    factoryMock = context.mock(EntityFactory.class, "factory");
    testArena = new BasicArena(factoryMock);
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
        oneOf (objectMock1).render(null);
        oneOf (objectMock2).render(null);
      }
    });
    
    testArena.addObject(objectMock1);
    testArena.addObject(objectMock2);
    testArena.render(null);

    context.assertIsSatisfied();
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
