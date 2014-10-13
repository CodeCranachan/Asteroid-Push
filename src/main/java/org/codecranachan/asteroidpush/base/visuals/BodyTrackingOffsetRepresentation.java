package org.codecranachan.asteroidpush.base.visuals;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

public class BodyTrackingOffsetRepresentation implements Representation {

   private RigidBody trackedBody;
   private Representation representation;

   public BodyTrackingOffsetRepresentation(Representation representation, RigidBody tracked) {
      this.representation = representation;
      this.trackedBody = tracked;
   }

   public void render(Graphics g) {
      g.pushTransform();
      Arrow offset = trackedBody.getPosition(); 
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
