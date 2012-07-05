package org.skullforge.asteroidpush.test;

import org.skullforge.asteroidpush.*;
import org.skullforge.asteroidpush.arena.*;
import org.junit.*;
import org.jmock.*;
import static org.junit.Assert.*;

public class ArenaGameStateTest {
  private Arena arenaMock;
  private ArenaGameState arenaState;
  private Mockery context;

  @Before
  public void setUp() throws Exception {
    context = new Mockery();
    arenaMock = context.mock(Arena.class);
    arenaState = new ArenaGameState(arenaMock);
  }

  @Test
  public void testGetID() throws Exception {
    int expectedID = 1;
    assertEquals(expectedID, arenaState.getID());
  }

  @Test
  public void testInit() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf(arenaMock).init();
      }
    });
    
    arenaState.init(null, null);
    
    context.assertIsSatisfied();
  }

  @Test
  public void testDelegateRendering() throws Exception {
    context.checking(new Expectations() {
      {
        oneOf(arenaMock).render(null);
      }
    });

    arenaState.render(null, null, null);

    context.assertIsSatisfied();
  }

  @Test
  public void testDelegateUpdate() throws Exception {
    final int expectedDelta = 45;
    context.checking(new Expectations() {
      {
        oneOf(arenaMock).update(expectedDelta);
      }
    });

    arenaState.update(null, null, expectedDelta);

    context.assertIsSatisfied();
  }

}
