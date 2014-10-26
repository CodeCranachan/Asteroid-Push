package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class CircleRepresentation implements Representation {
   public Arrow arrow;
   public Color color;

   public CircleRepresentation(Arrow arrow, Color color) {
      this.arrow = arrow;
      this.color = color;
   }

   public void render(Graphics g) {
      float radius = arrow.getMagnitude();
      g.setColor(color);
      g.setLineWidth(2.0f);
      g.drawOval(arrow.getTail().x - radius,
                 arrow.getTail().y - radius,
                 radius * 2f,
                 radius * 2f);
   }

   public int getPriority() {
      return 0;
   }
}
