package org.skullforge.asteroidpush.ui;

import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.skullforge.asteroidpush.designer.ModuleToken;

public class TokenView extends BasicWidget {

   private ModuleToken token;

   public void setToken(ModuleToken token) {
      this.token = token;
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
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
