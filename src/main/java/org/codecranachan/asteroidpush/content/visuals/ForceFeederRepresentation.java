package org.codecranachan.asteroidpush.content.visuals;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ForceFeederRepresentation implements Representation {
   public Arrow force;

   public ForceFeederRepresentation(Arrow force) {
      this.force = force;
   }

   public void render(Graphics g) {
      g.setColor(Color.red);
      g.setLineWidth(1.5f);
      g.drawOval(force.getTail().x - 0.05f,
                 force.getTail().x - 0.05f,
                 0.1f,
                 0.1f);
   }

   public int getPriority() {
      return 0;
   }
}
