package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.skullforge.asteroidpush.designer.ControlModule;
import org.skullforge.asteroidpush.designer.GridCoordinate;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.ui.ShipDesignView;
import org.skullforge.asteroidpush.ui.Widget;

public class DesignerGameState extends BasicGameState {

   public DesignerGameState(ShipDesign design, ResourceLoader resourceLoader) {
      this.resourceLoader = resourceLoader;
      this.shipDesign = design;
      this.ui = new ShipDesignView(shipDesign,
            this.resourceLoader.loadFont("Alfphabet-IV.ttf", 10));
   }

   @Override
   public void init(GameContainer container, StateBasedGame game)
         throws SlickException {
      this.game = game;
      shipDesign.addModule(new GridCoordinate(2, 2), new ControlModule());
   }

   @Override
   public void render(GameContainer container,
                      StateBasedGame game,
                      Graphics graphics) throws SlickException {
      Rectangle canvas = new Rectangle(0.0f, 0.0f, container.getWidth(),
            container.getHeight());
      ui.render(graphics, canvas);
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

   private ResourceLoader resourceLoader;
   private ShipDesign shipDesign;
   private Widget ui;
   private StateBasedGame game;
}
