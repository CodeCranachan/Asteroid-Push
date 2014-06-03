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

package org.codecranachan.asteroidpush.ui;

import org.codecranachan.asteroidpush.designer.BlueprintManipulator;
import org.codecranachan.asteroidpush.designer.ModuleToken;
import org.codecranachan.asteroidpush.designer.grid.Placement;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class SelectionView extends BasicWidget {

   private BlueprintManipulator manipulator;

   public SelectionView(BlueprintManipulator manipulator) {
      this.manipulator = manipulator;
   }

   @Override
   public void render(Graphics g) {
      TokenView tokenWidget = new TokenView();
      ModuleToken token = new ModuleToken(manipulator.getSelection());
      token.setPlacement(new Placement(0, 0, manipulator.getRotation()
            .getFacing()));
      tokenWidget.setToken(token);

      Rectangle frame = getCenteredSquare(getFrame());

      tokenWidget.resize(frame);
      tokenWidget.render(g);
   }

   public Rectangle getCenteredSquare(Rectangle parentFrame) {
      float sideLength = Math.min(parentFrame.getWidth(),
                                  parentFrame.getHeight());

      float offsetX = parentFrame.getX();
      offsetX += (parentFrame.getWidth() - sideLength) / 2.0f;
      float offsetY = parentFrame.getY();
      offsetY += (parentFrame.getHeight() - sideLength) / 2.0f;

      Rectangle rect = new Rectangle(offsetX, offsetY, sideLength, sideLength);

      return rect;
   }
}
