package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ExhaustRepresentation implements Representation {
   public Arrow offset;

   private final float radius = 0.75f;

   public ExhaustRepresentation(Arrow arrow) {
      this.offset = arrow;
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
      Angle rot = offset.getAngle();
      Angle arc_start = rot.add(Angle.PI.mul(3f / 4f));
      Angle arc_end = rot.sub(Angle.PI.mul(3f / 4f));

      g.drawArc(offset.getTail().x - radius * size,
                offset.getTail().y - radius * size,
                radius * 2f * size,
                radius * 2f * size,
                arc_start.deg(),
                arc_end.deg());
   }

   public int getPriority() {
      return 10;
   }
}
