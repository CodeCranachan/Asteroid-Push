package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.ui.IngameUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

public class MatchGameState extends BasicGameState {

   public MatchGameState(Simulator simulator, ResourceLoader resourceLoader) {
      this.simulator = simulator;
      this.timekeeper = new Timekeeper(simulator.getTimeStep());
      this.resourceLoader = resourceLoader;
      this.localPlayer = null;
   }

   public void setScenario(Scenario scenario) {
      this.scenario = scenario;
   }

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      simulator.initialize(scenario);
      localPlayer = new Player();
      localPlayer.init(simulator, new IngameUiFactory(resourceLoader));
      this.game = game;
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      Widget ui = localPlayer.getUi();
      if (ui != null) {
         ui.render(graphics, canvas);
      }
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

   @Override
   public void keyPressed(int key, char c) {
      if (Input.KEY_ESCAPE == key) {
         game.enterState(StateInfo.DESIGNER.getID());
      }
   }

   @Override
   public void keyReleased(int key, char c) {

   }

   private Simulator simulator;
   private Timekeeper timekeeper;
   private ResourceLoader resourceLoader;
   private Player localPlayer;
   private Scenario scenario;
   private StateBasedGame game;
}
