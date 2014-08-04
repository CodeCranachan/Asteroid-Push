package org.codecranachan.asteroidpush.visuals.actors;

import org.codecranachan.asteroidpush.visuals.Representation;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class PointerRepresentation implements Representation {
   public Vec2 position;

   public PointerRepresentation(Vec2 position) {
      this.position = position;
   }

   public void render(Graphics g) {
      g.setColor(Color.red);
      float size = 0.5f;
      g.fillOval(position.x - size / 2, position.y - size / 2, size, size);
   }

   public int getPriority() {
      return 3;
   }
}
