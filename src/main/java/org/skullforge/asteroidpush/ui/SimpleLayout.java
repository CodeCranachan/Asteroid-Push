//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
