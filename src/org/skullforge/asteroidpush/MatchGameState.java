package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MatchGameState extends BasicGameState {

   public MatchGameState(Simulator sim) {
      matchSimulator = sim;
      timekeeper = new Timekeeper(sim.getTimeStep());
   }

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      matchSimulator.initialize(new Scenario());
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
      timekeeper.addRealTime(delta);
      matchSimulator.stepToFrame(timekeeper.getGameTime());
   }

   @Override
   public int getID() {
      return StateInfo.MATCH.getID();
   }

   private Simulator matchSimulator;
   private Timekeeper timekeeper;
}
