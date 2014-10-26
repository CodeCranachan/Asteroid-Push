package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ArrowRepresentation implements Representation {
   private final float radius = 0.075f;

   public Arrow arrow;
   public Color color;

   public ArrowRepresentation(Arrow arrow, Color color) {
      this.arrow = arrow;
      this.color = color;
   }

   public void render(Graphics g) {
      g.setColor(color);
      g.setLineWidth(2.0f);

      float arc_start = this.arrow.getAngle() + MathUtils.PI / 3;
      float arc_end = this.arrow.getAngle() - MathUtils.PI / 3;

      g.drawArc(arrow.getTail().x - radius,
                arrow.getTail().y - radius,
                radius * 2f,
                radius * 2f,
                Trigonometry.radToDeg(arc_start),
                Trigonometry.radToDeg(arc_end));

      float tip_x = MathUtils.sqrt(2 * radius * radius) * 1.5f;
      float tip_y = 0;

      g.drawLine(arrow.getTail().x + MathUtils.cos(arc_start) * radius,
                 arrow.getTail().y + MathUtils.sin(arc_start) * radius,
                 tip_x,
                 tip_y);
      g.drawLine(arrow.getTail().x + MathUtils.cos(arc_end) * radius,
                 arrow.getTail().y + MathUtils.sin(arc_end) * radius,
                 tip_x,
                 tip_y);
   }

   public int getPriority() {
      return 0;
   }
}
