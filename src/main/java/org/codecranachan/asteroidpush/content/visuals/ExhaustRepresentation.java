package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ExhaustRepresentation implements Representation {
   public Arrow offset;

   private final float radius = 0.75f;

   public ExhaustRepresentation(Arrow offset) {
      this.offset = offset;
   }

   public void render(Graphics g) {
      g.setColor(Color.red);
      g.setLineWidth(1.0f);
      drawExhaust(g, 3f / 3f);

      g.setColor(Color.orange);
      g.setLineWidth(1.5f);
      drawExhaust(g, 2f / 3f);

      g.setColor(Color.yellow);
      g.setLineWidth(2.0f);
      drawExhaust(g, 1f / 3f);
   }

   private void drawExhaust(Graphics g, float size) {
      float arc_start = this.offset.getAngle() + MathUtils.PI * 3f / 4f;
      float arc_end = this.offset.getAngle() - MathUtils.PI * 3f / 4f;

      g.drawArc(offset.getTail().x - radius * size,
                offset.getTail().y - radius * size,
                radius * 2f * size,
                radius * 2f * size,
                Trigonometry.radToDeg(arc_start),
                Trigonometry.radToDeg(arc_end));
   }

   public int getPriority() {
      return 10;
   }
}
