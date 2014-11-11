package org.codecranachan.asteroidpush.base.visuals;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

public class OffsetRepresentation implements Representation {

   private Arrow offset;
   private float scale;
   private Representation representation;

   public OffsetRepresentation(Representation representation, Arrow offset,
         float scale) {
      this.representation = representation;
      this.offset = offset;
      this.scale = scale;
   }

   public void render(Graphics g) {
      g.pushTransform();
      Vec2 tail = offset.getTail();
      g.translate(tail.x, tail.y);
      g.rotate(0, 0, offset.getAngle().deg());
      g.scale(scale, scale);
      representation.render(g);
      g.popTransform();
   }

   public int getPriority() {
      return representation.getPriority();
   }

}
