package org.skullforge.asteroidpush;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.ArenaGameState;
import org.skullforge.asteroidpush.GameStateFactory;
import org.skullforge.asteroidpush.arena.Arena;
import org.skullforge.asteroidpush.arena.BasicArena;
import org.skullforge.asteroidpush.arena.BasicEntityFactory;
import org.skullforge.asteroidpush.arena.EntityFactory;

public class GameStateFactoryTest {
   GameStateFactory testFactory;

   @Before
   public void setUp() throws Exception {
      testFactory = new GameStateFactory();
   }
   @Test
   public void testInvalidStateCreation() throws Exception {
      GameState testState;
      testState = testFactory.createGameState(null);
      assertNull(testState);
      testState = testFactory.createGameState(StateInfo.INVALID);
      assertNull(testState);
   }
   
   @Test
   public void testArenaStateCreation() throws Exception {
      GameState testState = testFactory.createGameState(StateInfo.ARENA);

      assertEquals(testState.getClass(), ArenaGameState.class);
      ArenaGameState testArenaState = (ArenaGameState) testState;

      Arena testArena = testArenaState.getArena();
      assertNotNull(testArena);
      assertEquals(testArena.getClass(), BasicArena.class);
      BasicArena testBasicArena = (BasicArena) testArena;
      EntityFactory testEntityFactory = testBasicArena.getFactory();
      assertNotNull(testEntityFactory);
      assertEquals(testEntityFactory.getClass(), BasicEntityFactory.class);
   }
   
   @Test
   public void testMatchStateCreation() throws Exception {
      GameState testState = testFactory.createGameState(StateInfo.MATCH);
      assertEquals(testState.getClass(), MatchGameState.class);
   }

}
