package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.input.ControlItem;
import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.content.visuals.ExhaustRepresentation;
import org.codecranachan.asteroidpush.utils.Force;
import org.jbox2d.common.Vec2;

public class ForceFeederBehavior implements Behavior, InteractionHandler {
   private Force force;
   private RigidBody currentBody;
   private Controller controller;

   private boolean isActive;

   public ForceFeederBehavior(Force force) {
      this.force = force;
      this.currentBody = null;
      this.isActive = false;
   }

   public Collection<Command> update(int frame) {
      if (controller != null && currentBody != null) {
         float factor = controlMagnitude(frame);
         currentBody.applyForce(force.scaleForce(factor));
         isActive = (factor > 0.1f);
      }

      return new LinkedList<Command>();
   }

   private float controlMagnitude(int frame) {
      Vec2 forward = new Vec2(1f, 0f);
      Vec2 backward = new Vec2(-1f, 0f);
      Vec2 left = new Vec2(0f, 1f);
      Vec2 right = new Vec2(0f, -1f);

      forward
            .mulLocal(controller.getControl(ControlItem.FORWARD_THRUST, frame));
      backward.mulLocal(controller.getControl(ControlItem.BACKWARD_THRUST,
                                              frame));
      left.mulLocal(controller.getControl(ControlItem.LEFT_THRUST, frame));
      right.mulLocal(controller.getControl(ControlItem.RIGHT_THRUST, frame));

      Vec2 norm = new Vec2(force.getForce());
      norm.normalize();

      float mag_fwd = Vec2.dot(forward, norm);
      if (mag_fwd < 0f)
         mag_fwd = 0f;
      float mag_bwd = Vec2.dot(backward, norm);
      if (mag_bwd < 0f)
         mag_bwd = 0f;
      float mag_lft = Vec2.dot(left, norm);
      if (mag_lft < 0f)
         mag_lft = 0f;
      float mag_rgt = Vec2.dot(right, norm);
      if (mag_rgt < 0f)
         mag_rgt = 0f;

      float mag = mag_fwd + mag_bwd + mag_lft + mag_rgt;

      return mag;
   }

   public void onDetach(RigidBody body, int index) {
      assert index == 0;
      assert body == currentBody;
      currentBody = null;
   }

   public void onAttach(RigidBody body, int index) {
      assert index == 0;
      assert currentBody == null;
      currentBody = body;
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      if (currentBody != null && isActive) {
         Force transformed = currentBody.transformToWorld(force);
         reps.add(new ExhaustRepresentation(transformed.asArrow()));
      }
      return reps;
   }

   public void setController(Controller controller, int index) {
      assert index == 0;
      this.controller = controller;
   }

}
