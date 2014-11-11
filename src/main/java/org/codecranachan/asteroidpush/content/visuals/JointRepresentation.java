package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class JointRepresentation implements Representation {
   private Vec2 start;
   private Vec2 end;
   private Color color;

   public JointRepresentation(Vec2 start, Vec2 end, Color color) {
      this.start = start;
      this.end = end;
      this.color = color;
   }

   public void render(Graphics g) {
      float radius = 0.10f;
      g.setColor(color);
      g.setLineWidth(2.0f);
      g.drawOval(start.x - radius, start.y - radius, radius * 2f, radius * 2f);
      g.drawOval(end.x - radius, end.y - radius, radius * 2f, radius * 2f);
      
      Vec2 diff = end.sub(start);
      diff.normalize();
      diff.mulLocal(radius);
      g.drawLine(start.add(diff).x, start.add(diff).y, end.sub(diff).x, end.sub(diff).y);
   }

   public int getPriority() {
      return 0;
   }
}
