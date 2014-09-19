package org.codecranachan.asteroidpush.visuals;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

public class OffsetRepresentation implements Representation {

   private Arrow offset;
   private Representation representation;

   public OffsetRepresentation(Representation representation, Arrow offset) {
      this.representation = representation;
      this.offset = offset;
   }

   public void render(Graphics g) {
      g.pushTransform();
      Vec2 tail = offset.getTail();
      g.translate(tail.x, tail.y);
      g.rotate(0, 0, Trigonometry.radToDeg(offset.getAngle()));
      g.scale(offset.getMagnitude(), offset.getMagnitude());
      representation.render(g);
      g.popTransform();
   }

   public int getPriority() {
      return representation.getPriority();
   }

}
