package org.codecranachan.asteroidpush.base.ui.workshop;

import java.util.Vector;

import org.codecranachan.asteroidpush.base.ui.widget.BasicWidget;
import org.codecranachan.asteroidpush.base.ui.widget.Label;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.WorkshopCoordinator;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class SelectorWidget extends BasicWidget {
   private WorkshopCoordinator coordinator;
   private Label itemLabel;

   public SelectorWidget(WorkshopCoordinator coordinator, Font font) {
      this.coordinator = coordinator;
      this.itemLabel = new Label();
      itemLabel.setFont(font);
   }

   @Override
   public void render(Graphics g) {
      if (getFrame() == null) {
         return;
      }

      Vector<PartFactory> factories = coordinator.getAvailablePartFactories();
      for (int index = 0; index < factories.size(); index++) {
         itemLabel.resize(getItemFrame(index));

         itemLabel.setText(factories.get(index).getName());
         if (coordinator.getSelectedPartFactory() == factories.get(index)) {
            itemLabel.setBackgroundColor(Color.blue);
            itemLabel.setForegroundColor(Color.black);
         } else {
            itemLabel.setBackgroundColor(Color.black);
            itemLabel.setForegroundColor(Color.white);
         }

         if (getHoveredIndex() == index) {
            itemLabel.setBorderColor(Color.white);
         } else {
            itemLabel.setBorderColor(Color.blue);
         }

         itemLabel.render(g);
      }
   }

   private Rectangle getItemFrame(int index) {
      float itemHeight = getItemHeight();
      float y = (itemHeight) * index;
      return new Rectangle(getFrame().getMinX(), y, getFrame().getWidth(),
            itemHeight);
   }

   private float getItemHeight() {
      return (float) itemLabel.getFont().getLineHeight() * 1.5f;
   }

   private int getHoveredIndex() {
      Vector2f hover = getHover();
      if (hover == null) {
         return -1;
      } else {
         return getMousedIndex(hover);
      }
   }

   private int getMousedIndex(Vector2f cursor) {
      float index = (cursor.getY() - getFrame().getMinY()) / getItemHeight();
      return (int) index;
   }

   @Override
   public void mousePressed(int button, int x, int y) {
      if (getFrame() == null) {
         return;
      }

      int index = getMousedIndex(new Vector2f(x, y));

      switch (button) {
      case Input.MOUSE_LEFT_BUTTON:
         coordinator.selectPart(index);
         break;
      case Input.MOUSE_RIGHT_BUTTON:
         coordinator.clearSelection();
         break;
      }
   }
}
