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

import org.codecranachan.asteroidpush.designer.ModuleToken;
import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class TokenView extends BasicWidget {

   private ModuleToken token;

   public void setToken(ModuleToken token) {
      this.token = token;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      g.setLineWidth(1.5f);
      g.setColor(Color.white);
      for (Shape shape : token.getOutline()) {
         Transform translation = Transform.createTranslateTransform(frame
               .getCenterX(), frame.getCenterY());
         Transform scale = Transform
               .createScaleTransform(frame.getWidth() * 0.8f,
                                     -frame.getHeight() * 0.8f);
         Transform rotation = Transform.createRotateTransform(token
               .getRotation() + MathUtils.HALF_PI);
         shape = shape.transform(rotation);
         shape = shape.transform(scale);
         shape = shape.transform(translation);
         g.draw(shape);
      }
   }

}
