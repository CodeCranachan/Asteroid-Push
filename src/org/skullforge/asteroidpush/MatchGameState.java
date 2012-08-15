package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.ui.layouts.Layout;

public class MatchGameState extends BasicGameState {

   public MatchGameState(Simulator simulator, Layout layout) {
      this.simulator = simulator;
      this.layout = layout;
      timekeeper = new Timekeeper(simulator.getTimeStep());
   }

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      simulator.initialize(new Scenario());
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      layout.render(container, graphics);
   }

   @Override
   public void update(GameContainer container, StateBasedGame game, int delta)
         throws SlickException {
      timekeeper.addRealTime(delta);
      simulator.stepToFrame(timekeeper.getGameTime());
   }

   @Override
   public int getID() {
      return StateInfo.MATCH.getID();
   }

   private Simulator simulator;
   private Timekeeper timekeeper;
   private Layout layout;
}
