package org.skullforge.omegazone.test;

import org.skullforge.omegazone.arena.*;
import org.jmock.*;
import org.junit.*;

public class BasicArenaTest {
  Mockery context;
  BasicArena testArena;
  ArenaObject objectMock1;
  ArenaObject objectMock2;

  @Before
  public void setUp() {
    context = new Mockery();
    testArena = new BasicArena();
    objectMock1 = context.mock(ArenaObject.class, "object1");
    objectMock2 = context.mock(ArenaObject.class, "object2");
  }
  
  @Test
  public void testDelegatingRenderOperation() {
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
  public void testDelegatingUpdateOperation() {
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
