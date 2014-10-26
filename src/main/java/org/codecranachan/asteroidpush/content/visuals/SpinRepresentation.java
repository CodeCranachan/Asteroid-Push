package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SpinRepresentation implements Representation {
   private final float radius = 0.2f;

   public Arrow arrow;
   public Color color;

   public SpinRepresentation(Arrow arrow, Color color) {
      this.arrow = arrow;
      this.color = color;
   }

   public void render(Graphics g) {
      g.setColor(color);
      g.setLineWidth(2.0f);
      drawArrow(g, MathUtils.PI / 4f, MathUtils.HALF_PI);
      drawArrow(g, MathUtils.PI * 5f / 4f, MathUtils.HALF_PI);
   }

   private void drawArrow(Graphics g, float start, float arc_len) {
      g.drawArc(arrow.getTail().x - radius,
                arrow.getTail().y - radius,
                radius * 2f,
                radius * 2f,
                Trigonometry.radToDeg(start),
                Trigonometry.radToDeg(start + arc_len));

      float tip_x = arrow.getTail().x + MathUtils.cos(start) * radius;
      float tip_y = arrow.getTail().y + MathUtils.sin(start) * radius;

      float tail_x_1 = arrow.getTail().x
            + MathUtils.cos(start + arc_len * 2f / 10f) * radius * 0.8f;
      float tail_y_1 = arrow.getTail().y
            + MathUtils.sin(start + arc_len * 2f / 10f) * radius * 0.8f;
      g.drawLine(tip_x, tip_y, tail_x_1, tail_y_1);

      float tail_x_2 = arrow.getTail().x
            + MathUtils.cos(start + arc_len * 2f / 10f) * radius * 1.2f;
      float tail_y_2 = arrow.getTail().y
            + MathUtils.sin(start + arc_len * 2f / 10f) * radius * 1.2f;
      g.drawLine(tip_x, tip_y, tail_x_2, tail_y_2);
   }

   public int getPriority() {
      return 0;
   }
}
