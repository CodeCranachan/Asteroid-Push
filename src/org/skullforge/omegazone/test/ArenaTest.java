package org.skullforge.omegazone.test;

import org.skullforge.omegazone.arena.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

public class ArenaTest {
  Mockery context = new Mockery();

  @Test
  public void testDelegatingRenderOperation() {
    Arena testArena = new Arena();
    final ArenaObject object1 = context.mock(ArenaObject.class, "object1");
    final ArenaObject object2 = context.mock(ArenaObject.class, "object2");

    context.checking(new Expectations() {
      {
        oneOf (object1).render(null);
        oneOf (object2).render(null);
      }
    });
    
    testArena.addObject(object1);
    testArena.addObject(object2);
    testArena.render(null);

    context.assertIsSatisfied();
  }
  
  @Test
  public void testDelegatingUpdateOperation() {
    Arena testArena = new Arena();
    final ArenaObject object1 = context.mock(ArenaObject.class, "object1");
    final ArenaObject object2 = context.mock(ArenaObject.class, "object2");    
    final int expectedDelta = 25;
    
    context.checking(new Expectations() {{
      oneOf (object1).update(expectedDelta);
      oneOf (object2).update(expectedDelta);
    }});
    
    testArena.addObject(object1);
    testArena.addObject(object2);
    testArena.update(expectedDelta);
    
    context.assertIsSatisfied();
  }
}
