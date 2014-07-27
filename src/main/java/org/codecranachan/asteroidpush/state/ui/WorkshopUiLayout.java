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

package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.codecranachan.asteroidpush.visuals.widget.Widget;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class WorkshopUiLayout extends BasicWidget {

   Widget catalogue;
   Widget blueprint;
   Widget selection;
   final float blueprintRatio = 0.75f;
   final float catalogueRatio = 0.75f;

   public WorkshopUiLayout() {
      this.catalogue = null;
      this.blueprint = null;
      this.selection = null;
   }

   public void setCatalogueWidget(Widget widget) {
      this.catalogue = widget;
   }

   public void setBlueprintWidget(Widget widget) {
      this.blueprint = widget;
   }

   public void setSelectionWidget(Widget widget) {
      this.selection = widget;
   }

   @Override
   public void resize(Rectangle frame) {
      super.resize(frame);

      if (blueprint != null) {
         blueprint.resize(getBlueprintFrame(getFrame()));
      }
      if (catalogue != null) {
         catalogue.resize(getCatalogueFrame(getFrame()));
      }
      if (selection != null) {
         selection.resize(getSelectionFrame(getFrame()));
      }
   }

   @Override
   public void setHover(float x, float y) {
      super.setHover(x, y);
      if (blueprint != null) {
         if (getBlueprintFrame(getFrame()).contains(x, y)) {
            blueprint.setHover(x, y);
         } else {
            blueprint.resetHover();
         }
      }
      if (catalogue != null) {
         if (getCatalogueFrame(getFrame()).contains(x, y)) {
            catalogue.setHover(x, y);
         } else {
            catalogue.resetHover();
         }
      }
   }

   @Override
   public void resetHover() {
      super.resetHover();
      if (blueprint != null) {
         blueprint.resetHover();
      }
      if (catalogue != null) {
         catalogue.resetHover();
      }
   }

   @Override
   public void render(Graphics g) {
      if (catalogue != null) {
         g.setClip(getCatalogueFrame(getFrame()));
         catalogue.render(g);
      }
      if (selection != null) {
         g.setClip(getSelectionFrame(getFrame()));
         selection.render(g);
      }
      if (blueprint != null) {
         g.setClip(getBlueprintFrame(getFrame()));
         blueprint.render(g);
      }
      g.clearClip();
   }

   private Rectangle getBlueprintFrame(Rectangle frame) {
      return new Rectangle(frame.getX(), frame.getY(), frame.getWidth()
            * blueprintRatio, frame.getHeight());
   }

   private Rectangle getCatalogueFrame(Rectangle frame) {
      float x = frame.getWidth() * blueprintRatio;
      float y = frame.getY();
      float w = frame.getWidth() * (1.0f - blueprintRatio);
      float h = frame.getHeight() * catalogueRatio;
      return new Rectangle(x, y, w, h);
   }

   private Rectangle getSelectionFrame(Rectangle frame) {
      Rectangle catalogueFrame = getCatalogueFrame(frame);
      float x = catalogueFrame.getX();
      float y = catalogueFrame.getY() + catalogueFrame.getHeight();
      float w = catalogueFrame.getWidth();
      float h = frame.getHeight() - catalogueFrame.getHeight();
      return new Rectangle(x, y, w, h);
   }

   @Override
   public void mousePressed(int button, int x, int y) {
      if (blueprint != null && getBlueprintFrame(getFrame()).contains(x, y)) {
         blueprint.mousePressed(button, x, y);
      }
      if (catalogue != null && getCatalogueFrame(getFrame()).contains(x, y)) {
         catalogue.mousePressed(button, x, y);
      }
   }

}
