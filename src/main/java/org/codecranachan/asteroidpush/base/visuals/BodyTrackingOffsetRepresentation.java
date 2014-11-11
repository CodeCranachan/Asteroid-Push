package org.codecranachan.asteroidpush.base.visuals;

import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

public class BodyTrackingOffsetRepresentation implements Representation {

   private RigidBody trackedBody;
   private Representation representation;

   public BodyTrackingOffsetRepresentation(Representation representation,
         RigidBody tracked) {
      this.representation = representation;
      this.trackedBody = tracked;
   }

   public void render(Graphics g) {
      g.pushTransform();
      NewtonianState state = trackedBody.getState();
      Vec2 tail = state.getPosition();
      g.translate(tail.x, tail.y);
      g.rotate(0, 0, state.getRotation().deg());
      representation.render(g);
      g.popTransform();
   }

   public int getPriority() {
      return representation.getPriority();
   }

}
