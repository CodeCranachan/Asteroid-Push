package org.skullforge.omegazone.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.state.GameState;
import org.skullforge.omegazone.ArenaGameState;
import org.skullforge.omegazone.BasicStateFactory;
import org.skullforge.omegazone.StateFactory;
import org.skullforge.omegazone.arena.Arena;
import org.skullforge.omegazone.arena.BasicArena;
import org.skullforge.omegazone.arena.BasicEntityFactory;
import org.skullforge.omegazone.arena.EntityFactory;

public class BasicStateFactoryTest {
  StateFactory testFactory;
  
  @Before
  public void setUp() throws Exception {
    testFactory = new BasicStateFactory();
  }
  
  @Test
  public void testArenaStateCreation() throws Exception {
    GameState testState = testFactory.createArenaState();
    
    assertEquals(testState.getClass(), ArenaGameState.class);
    ArenaGameState testArenaState = (ArenaGameState)testState;
    
    Arena testArena = testArenaState.getArena();
    assertNotNull(testArena);
    assertEquals(testArena.getClass(), BasicArena.class);
    BasicArena testBasicArena = (BasicArena)testArena;
    EntityFactory testEntityFactory = testBasicArena.getFactory();
    assertNotNull(testEntityFactory);
    assertEquals(testEntityFactory.getClass(), BasicEntityFactory.class);
  }

}
