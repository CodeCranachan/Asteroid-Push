package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.ui.DesignerUiFactory;
import org.skullforge.asteroidpush.ui.Widget;

public class DesignerGameState extends BasicGameState {

   public DesignerGameState(DesignerUiFactory uiFactory) {
      this.uiFactory = uiFactory;
   }

   public void setScenario(Scenario scenario) {
      this.scenario = scenario;
   }

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      this.game = game;
      uiFactory.init(scenario.getLocalPlayer());
      ui = uiFactory.createUi();
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      if (ui != null) {
         ui.resize(canvas);
         ui.render(graphics);
      }
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
      switch (key) {
      case Input.KEY_SPACE:
         game.enterState(StateInfo.MATCH.getID());
         break;
      case Input.KEY_UP:
         scenario.getLocalPlayer().cycleShipDesign();
         break;
      }
   }

   @Override
   public void mouseMoved(int oldx, int oldy, int newx, int newy) {
      ui.setHover(newx, newy);
   }

   private Scenario scenario;
   private DesignerUiFactory uiFactory;
   private Widget ui;
   private StateBasedGame game;
}
