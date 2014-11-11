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
import org.codecranachan.asteroidpush.utils.NewtonianState;

public class ActorSpawnBehavior implements Behavior, InteractionHandler {
   private NewtonianState spawnState;
   private ActorFactory factory;
   private RigidBody currentBody;
   private Controller controller;
   private int lastSpawnFrame;

   static int SPAWN_INTERVAL = 60;

   public ActorSpawnBehavior(NewtonianState spawn_state,
         ActorFactory spawn_factory) {
      this.spawnState = spawn_state;
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
               currentBody.getState();
               NewtonianState initialState = currentBody
                     .transformToWorld(spawnState);
               commands.add(new CreateActorCommand(initialState, factory));
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
