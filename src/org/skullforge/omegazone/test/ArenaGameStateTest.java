package org.skullforge.omegazone.test;

import org.skullforge.omegazone.*;
import static org.junit.Assert.*;

import org.junit.Test;

import org.newdawn.slick.state.GameState;

public class ArenaGameStateTest {

	@Test
	public void testGetID() {
		GameState arenaState = State.ARENA.getGameState();
		assertEquals(State.ARENA.getID(), arenaState.getID());
	}

}
