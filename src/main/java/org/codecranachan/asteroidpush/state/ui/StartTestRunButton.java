package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.AsteroidPush;
import org.codecranachan.asteroidpush.simulation.TestRunScenario;
import org.codecranachan.asteroidpush.state.StateId;
import org.codecranachan.asteroidpush.state.TestRunContext;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.codecranachan.asteroidpush.workshop.assembly.SpaceshipFactory;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartTestRunButton extends BasicWidget {
   private Font font;
   private WorkshopCoordinator coordinator;
   private boolean doTestRun;
   
   static private String RUN_TEST_TEXT = "Test Run";
   static private String NO_SHIP_TEXT = "No Ship";
   static private String ERROR_TEXT = "StartTestRunButton: No coordinator set";

   public StartTestRunButton(WorkshopCoordinator coordinator, Font font) {
      this.font = font;
      this.coordinator = coordinator;
   }

   public void render(Graphics g) {
      String text;
      if (coordinator == null) {
         text = ERROR_TEXT;
         g.setColor(Color.red);
      }

      if (coordinator.getManipulatedBlueprint() == null) {
         text = NO_SHIP_TEXT;
         g.setColor(Color.darkGray);
      } else {
         text = RUN_TEST_TEXT;
         g.setColor(Color.yellow);
      }

      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      g.setFont(font);
      g.drawRoundRect(frame.getX(),
                      frame.getY(),
                      frame.getWidth() - 2,
                      frame.getHeight() - 2,
                      15);
      g.drawString(text, frame.getCenterX() - (float) font.getWidth(text)
            / 2.0f, frame.getCenterY() - (float) font.getHeight(text) / 2.0f);
      g.setFont(currentFont);
   }

   public void update(GameContainer container, StateBasedGame game, int delta) {
      if (doTestRun) {
         doTestRun = false;
         Blueprint prototype = coordinator.getManipulatedBlueprint();
         AsteroidPush push = (AsteroidPush) game;
         push.pushContext(new TestRunContext(prototype));
      }
   }

   public void mousePressed(int button, int x, int y) {
      if (coordinator == null) {
         return;
      }
      if (button == Input.MOUSE_LEFT_BUTTON && coordinator.getManipulatedBlueprint() != null) {
         doTestRun = true;
      }
   }
}
