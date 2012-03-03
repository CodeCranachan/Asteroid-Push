package org.skullforge.omegazone;

import org.newdawn.slick.state.GameState;

public enum State {
	ARENA(new ArenaGameState(), 1);
	
	private int stateID;
	private GameState stateGameState; 
	
	State(GameState state, int ID) {
		stateGameState = state;
		stateID = ID;
	}
	
	public int getID() {
		return stateID;
	}
	
	public GameState getGameState() {
		return stateGameState;
	}
}