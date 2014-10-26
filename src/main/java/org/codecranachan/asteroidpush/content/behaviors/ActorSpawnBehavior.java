package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.input.ControlItem;
import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.simulation.command.CreateActorCommand;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;
import org.jbox2d.common.Transform;

public class ActorSpawnBehavior implements Behavior, InteractionHandler {
   private Arrow offset;
   private Velocity velocity;
   private ActorFactory factory;
   private RigidBody currentBody;
   private Controller controller;
   private int lastSpawnFrame;

   static int SPAWN_INTERVAL = 60;

   public ActorSpawnBehavior(Arrow spawn_offset, Velocity spawn_velocity,
         ActorFactory spawn_factory) {
      this.offset = spawn_offset;
      this.velocity = spawn_velocity;
      this.factory = spawn_factory;
      this.currentBody = null;
      this.controller = null;
      this.lastSpawnFrame = -SPAWN_INTERVAL;
   }

   public Collection<Command> update(int frame) {
      LinkedList<Command> commands = new LinkedList<Command>();
      if (controller != null && currentBody != null) {
         if (controller.getControl(ControlItem.FIRE_PRIMARY, frame) > 0) {
            if (lastSpawnFrame + SPAWN_INTERVAL < frame) {
               lastSpawnFrame = frame;
               Arrow bodyPosition = currentBody.getPosition();
               Velocity bodyVelocity = currentBody.getVelocity();
               Velocity transformed_velocity = new Velocity(
                     Transform.mul(bodyPosition.getTransform(),
                                   velocity.getLinear()), velocity.getAngular());
               commands.add(new CreateActorCommand(offset
                     .applyTransform(bodyPosition), transformed_velocity
                     .add(bodyVelocity), factory));
            }
         }
      }

      return commands;
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
