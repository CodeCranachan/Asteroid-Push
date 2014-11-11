package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BorderRepresentation implements Representation {
   public float fieldWidth;
   public float fieldHeight;

   public BorderRepresentation(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }

   public void render(Graphics g) {
      g.setColor(Color.red);
      g.setLineWidth(1.0f);
      g.drawRect(-fieldWidth / 2f, -fieldHeight / 2f, fieldWidth, fieldHeight);
   }

   public int getPriority() {
      return 0;
   }
}
