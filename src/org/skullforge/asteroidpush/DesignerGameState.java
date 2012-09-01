package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DesignerGameState extends BasicGameState {

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      this.game = game;
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics)
         throws SlickException {
      // TODO Auto-generated method stub

   }

   @Override
   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      // TODO Auto-generated method stub

   }

   @Override
   public int getID() {
      return StateInfo.DESIGNER.getID();
   }

   @Override
   public void keyPressed(int key, char c) {
      if (Input.KEY_SPACE == key) {
         game.enterState(StateInfo.MATCH.getID());
      }
   }

   private StateBasedGame game;
}
