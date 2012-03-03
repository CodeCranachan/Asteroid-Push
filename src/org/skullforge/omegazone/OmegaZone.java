package org.skullforge.omegazone;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class OmegaZone extends StateBasedGame {

	public OmegaZone() {
		super("OmegaZone");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		for (State s : State.values()) {
			addState(s.getGameState());
		}
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new OmegaZone());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
