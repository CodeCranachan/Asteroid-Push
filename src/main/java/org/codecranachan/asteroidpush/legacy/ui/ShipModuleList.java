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

package org.codecranachan.asteroidpush.legacy.ui;

import java.util.Vector;

import org.codecranachan.asteroidpush.base.ui.widget.BasicWidget;
import org.codecranachan.asteroidpush.legacy.designer.BlueprintManipulator;
import org.codecranachan.asteroidpush.legacy.designer.catalogue.ModuleCatalogue;
import org.codecranachan.asteroidpush.legacy.designer.data.ModuleData;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ShipModuleList extends BasicWidget {
   private final float marginX = 3.0f;
   private final float marginY = 3.0f;

   Font font;
   Vector<ModuleData> moduleCatalogue;
   int selectedIndex;
   BlueprintManipulator manipulator;

   public ShipModuleList(BlueprintManipulator manipulator, ModuleCatalogue moduleCatalogue, Font font) {
      this.moduleCatalogue = new Vector<ModuleData>(moduleCatalogue.getModuleData());
      this.font = font;
      this.selectedIndex = 0;
      this.manipulator = manipulator;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      g.setFont(font);
      float top = frame.getY() + marginY;
      for (int index=0; index < this.moduleCatalogue.size(); index++) {
         if (index==selectedIndex) {
            g.setColor(Color.gray);
         } else {
            g.setColor(Color.black);
         }
         g.fillRect(frame.getX(), top, frame.getWidth(), font.getLineHeight());
         g.setColor(Color.white);
         g.drawString(this.moduleCatalogue.get(index).getName(), frame.getX() + marginX, top);
         top += g.getFont().getLineHeight();
      }
      g.setFont(currentFont);

      g.setLineWidth(2.0f);
      g.setColor(Color.gray);
      g.drawRoundRect(frame.getX(),
                      frame.getY(),
                      frame.getWidth(),
                      frame.getHeight(),
                      5);

   }

   @Override
   public void mousePressed(int button, int x, int y) {
      int newIndex = (int) ((y - getFrame().getY()) / font.getLineHeight());
      if (newIndex < this.moduleCatalogue.size()) {
         selectedIndex = newIndex;
         if (manipulator != null) {
            manipulator.select(this.moduleCatalogue.get(selectedIndex));
         }
      }
   }
}
