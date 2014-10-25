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
import org.codecranachan.asteroidpush.utils.Arrow;

public class ForceFeederBehavior implements Behavior, InteractionHandler {
   private Arrow force;
   private RigidBody currentBody;
   private Controller controller;

   public ForceFeederBehavior(Arrow force) {
      this.force = force;
      this.currentBody = null;
   }

   public Collection<Command> update(int frame) {
      if (controller != null && currentBody != null) {
         float factor = controller
               .getControl(ControlItem.FORWARD_THRUST, frame);
         currentBody.applyForce(calculateForce().applyScale(factor));
      }

      return new LinkedList<Command>();
   }

   private Arrow calculateForce() {
      Arrow body = currentBody.getPosition();
      return new Arrow(force.getTail().add(body.getTail()), force.getAngle()
            + body.getAngle(), force.getMagnitude());
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
      Collection<Representation> representations = new LinkedList<Representation>();
      return representations;
   }

   public void setController(Controller controller, int index) {
      assert index == 0;
      this.controller = controller;
   }

}
