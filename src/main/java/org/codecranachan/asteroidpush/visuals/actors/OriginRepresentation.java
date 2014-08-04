package org.codecranachan.asteroidpush.visuals.actors;

import org.codecranachan.asteroidpush.visuals.Representation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class OriginRepresentation implements Representation {

   public void render(Graphics g) {
      Color color = new Color(Color.white);
      color.a = 0.5f;
      g.setColor(color);
      g.setLineWidth(2.5f);
      g.drawLine(-0.1f, 0.0f, 0.4f, 0.0f);
      g.drawLine(0.0f, -0.1f, 0.0f, 0.2f);
   }

   public int getPriority() {
      return 5;
   }

}
