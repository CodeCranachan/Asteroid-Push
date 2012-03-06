package org.skullforge.omegazone.test;

import org.skullforge.omegazone.arena.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

public class ArenaTest {
  Mockery context = new Mockery();

  @Test
  public void testObjectManagement() {
    Arena testArena = new Arena();
    final ArenaObject object = context.mock(ArenaObject.class);

    context.checking(new Expectations() {
      {
        oneOf (object).render(null);
      }
    });
    
    testArena.addObject(object);
    testArena.render(null);

    context.assertIsSatisfied();
  }
}
