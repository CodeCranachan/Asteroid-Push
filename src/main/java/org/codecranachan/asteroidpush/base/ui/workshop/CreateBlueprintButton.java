package org.codecranachan.asteroidpush.base.ui.workshop;

import org.codecranachan.asteroidpush.base.ui.widget.BasicWidget;
import org.codecranachan.asteroidpush.base.workshop.WorkshopCoordinator;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class CreateBlueprintButton extends BasicWidget {
   private Font font;
   private WorkshopCoordinator coordinator;
   static private String CREATE_TEXT = "< Click to create new blueprint >";
   static private String ERROR_TEXT = "CreateBlueprintButton: No coordinator set";

   public CreateBlueprintButton(WorkshopCoordinator coordinator, Font font) {
      this.font = font;
      this.coordinator = coordinator;
   }

   @Override
   public void render(Graphics g) {
      String text;
      if (coordinator == null) {
         text = ERROR_TEXT;
         g.setColor(Color.red);
      } else {
         text = CREATE_TEXT;
         g.setColor(Color.green);
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

   @Override
   public void mousePressed(int button, int x, int y) {
      if (coordinator == null) {
         return;
      }
      if (button == Input.MOUSE_LEFT_BUTTON) {
         coordinator.createNewBlueprint();
      }
   }
}
