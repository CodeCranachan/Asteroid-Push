package org.skullforge.asteroidpush.ui;

import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class SimpleLayout implements Widget {

   public SimpleLayout() {
      elements = new HashMap<String, SimpleLayout.LayoutElement>(2);

      LayoutElement backgroundElement = new LayoutElement(null);
      elements.put("background", backgroundElement);

      Rectangle infoRectangle = new Rectangle(400, 20, 220, 60);
      LayoutElement infoElement = new LayoutElement(infoRectangle);
      elements.put("info", infoElement);
   }

   @Override
   public void render(Graphics graphics, Rectangle frame) {
      Collection<LayoutElement> elementCollection = elements.values();
      for (LayoutElement element : elementCollection) {
         Widget widget = element.getWidget();
         Rectangle target = element.getPosition();
         if (target == null) {
            target = new Rectangle(0.0f, 0.0f, frame.getWidth(),
                  frame.getHeight());
         }
         if (widget == null) {
            drawMissingWidget(graphics, target);
         } else {
            widget.render(graphics, target);
         }
      }
   }

   private void drawMissingWidget(Graphics graphics, Rectangle frame) {
      graphics.setColor(Color.black);
      graphics.fillRoundRect(frame.getX(),
                             frame.getY(),
                             frame.getWidth() - 1.0f,
                             frame.getHeight() - 1.0f,
                             15);
      graphics.setColor(Color.gray);
      graphics.drawRoundRect(frame.getX(),
                             frame.getY(),
                             frame.getWidth() - 1.0f,
                             frame.getHeight() - 1.0f,
                             15);
      graphics.setColor(Color.red);
      graphics.drawString("Widget Missing", frame.getX() + 10.0f, frame.getY()
            + frame.getHeight() - 25.0f);
   }

   public void setWidget(String elementName, Widget widget) {
      LayoutElement element = elements.get(elementName);
      if (element != null) {
         element.setWidget(widget);
      }
   }

   HashMap<String, LayoutElement> elements;

   private class LayoutElement {
      public LayoutElement(Rectangle position) {
         this.widget = null;
         this.position = position;
      }

      public Widget getWidget() {
         return widget;
      }

      public void setWidget(Widget widget) {
         this.widget = widget;
      }

      public Rectangle getPosition() {
         return position;
      }

      private Widget widget;
      private Rectangle position;
   }
}
