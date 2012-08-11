package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MatchGameState extends BasicGameState {

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      // Initialize model with scenario
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      // Delegate to view abstraction
   }

   @Override
   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      // Delegate to model abstraction
   }

   @Override
   public int getID() {
      return StateInfo.MATCH.getID();
   }

}
