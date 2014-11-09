package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.Collection;
import java.util.Set;

import org.codecranachan.asteroidpush.base.input.ControlItem;
import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.Actor;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.FieldOfView;

public class ModularActor implements Actor {
   private ActorSkeleton skeleton;
   private Controller controller;

   public ModularActor(ActorSkeleton skeleton) {
      this.skeleton = skeleton;
      this.controller = null;
   }

   public Collection<Command> update(int frameNumber) {
      if (controller != null) {
         if (controller.getControl(ControlItem.FIRE_PRIMARY, frameNumber) > 0f) {
            skeleton.explode();
         }
      }

      return skeleton.update(frameNumber);
   }

   public Collection<Representation> getRepresentations() {
      return skeleton.getRepresentations();
   }

   public FieldOfView getFieldOfView() {
      Set<RigidBody> bodies = skeleton.getBodies();
      FieldOfView focus = new FieldOfView();
      for (RigidBody body : bodies) {
         Circle circle = body.getEnclosingCircle();
         circle.addRadius(10.0f);
         focus = new FieldOfView(circle, Angle.HALF_PI);
      }
      return focus;
   }

   public void destroy() {
      // TODO Detach all behaviors from their bodies and destroy bodies
   }

   public void setController(Controller controller) {
      skeleton.setController(controller);
      this.controller = controller;
   }

}
