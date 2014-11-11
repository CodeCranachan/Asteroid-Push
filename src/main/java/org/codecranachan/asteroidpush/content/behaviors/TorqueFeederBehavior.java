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

public class TorqueFeederBehavior implements Behavior, InteractionHandler {
   private float torque;
   private RigidBody currentBody;
   private Controller controller;

   public TorqueFeederBehavior(float torque) {
      this.torque = torque;
      this.currentBody = null;
   }

   public Collection<Command> update(int frame) {
      if (controller != null && currentBody != null) {
         float factor = controlMagnitude(frame);
         currentBody.applyTorque(factor * torque);
      }

      return new LinkedList<Command>();
   }

   private float controlMagnitude(int frame) {
      return controller.getControl(ControlItem.ROTATE_COUNTERCLOCKWISE, frame)
            - controller.getControl(ControlItem.ROTATE_CLOCKWISE, frame);
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
      return reps;
   }

   public void setController(Controller controller, int index) {
      assert index == 0;
      this.controller = controller;
   }

}
