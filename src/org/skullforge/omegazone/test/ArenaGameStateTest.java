package org.skullforge.omegazone.test;

import org.newdawn.slick.SlickException;
import org.skullforge.omegazone.*;
import org.skullforge.omegazone.arena.*;
import org.junit.*;
import org.jmock.*;
import static org.junit.Assert.*;

public class ArenaGameStateTest {
  private Arena arenaMock;
  private ArenaGameState arenaState;
  private Mockery context;

  @Before
  public void setUp() {
    context = new Mockery();
    arenaMock = context.mock(Arena.class);
    arenaState = new ArenaGameState(arenaMock);
  }

  @Test
  public void testGetID() {
    int expectedID = 1;
    assertEquals(expectedID, arenaState.getID());
  }

  @Test
  public void testInit() throws SlickException {
    arenaState.init(null, null);
  }

  @Test
  public void testDelegateRendering() throws SlickException {
    context.checking(new Expectations() {
      {
        oneOf(arenaMock).render(null);
      }
    });

    arenaState.render(null, null, null);

    context.assertIsSatisfied();
  }

  @Test
  public void testDelegateUpdate() throws SlickException {
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
