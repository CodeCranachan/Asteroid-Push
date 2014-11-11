package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Circle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class CircleRepresentation implements Representation {
   public Circle circle;
   public Color color;

   public CircleRepresentation(Circle circle, Color color) {
      this.circle = circle;
      this.color = color;
   }

   public void render(Graphics g) {
      float radius = circle.getRadius();
      g.setColor(color);
      g.setLineWidth(2.0f);
      g.drawOval(circle.getCenter().x - radius,
                 circle.getCenter().y - radius,
                 radius * 2f,
                 radius * 2f);
   }

   public int getPriority() {
      return 0;
   }
}
