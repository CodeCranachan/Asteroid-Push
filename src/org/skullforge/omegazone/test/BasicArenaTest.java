package org.skullforge.omegazone.test;

import org.skullforge.omegazone.arena.*;
import org.jmock.*;
import org.junit.*;

public class BasicArenaTest {
  Mockery context;
  BasicArena testArena;
  ArenaObjectFactory factoryMock;
  ArenaObject objectMock1;
  ArenaObject objectMock2;

  @Before
  public void setUp() throws Exception {
    context = new Mockery();
    factoryMock = context.mock(ArenaObjectFactory.class, "factory");
    testArena = new BasicArena(factoryMock);
    objectMock1 = context.mock(ArenaObject.class, "object1");
    objectMock2 = context.mock(ArenaObject.class, "object2");
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
