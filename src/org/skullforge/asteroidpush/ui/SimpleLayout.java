package org.skullforge.asteroidpush.ui;

import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class SimpleLayout extends BasicWidget {

   public SimpleLayout() {
      elements = new HashMap<String, SimpleLayout.LayoutElement>();

      Rectangle infoRectangle = new Rectangle(400, 20, 220, 30);
      LayoutElement infoElement = new LayoutElement(infoRectangle);
      elements.put("info", infoElement);

      Rectangle controlRectangle = new Rectangle(400, 50, 220, 30);
      LayoutElement controlElement = new LayoutElement(controlRectangle);
      elements.put("control", controlElement);
   }

   @Override
   public void resize(Rectangle frame) {
      super.resize(frame);
      if (background != null) {
         background.resize(frame);
      }

      Collection<LayoutElement> elementCollection = elements.values();
      for (LayoutElement element : elementCollection) {
         Widget widget = element.getWidget();
         Rectangle target = element.getPosition();
         if (target == null) {
            target = new Rectangle(0.0f, 0.0f, frame.getWidth(),
                  frame.getHeight());
         }
         if (widget != null) {
            widget.resize(target);
         }
      }
   }

   @Override
   public void render(Graphics graphics) {
      if (background != null) {
         background.render(graphics);
      }

      Collection<LayoutElement> elementCollection = elements.values();
      for (LayoutElement element : elementCollection) {
         Widget widget = element.getWidget();
         if (widget != null) {
            widget.render(graphics);
         }
      }
   }

   public void setWidget(String elementName, Widget widget) {
      LayoutElement element = elements.get(elementName);
      if (element != null) {
         element.setWidget(widget);
      }
   }

   public void setBackground(Widget widget) {
      background = widget;
   }

   Widget background;
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
