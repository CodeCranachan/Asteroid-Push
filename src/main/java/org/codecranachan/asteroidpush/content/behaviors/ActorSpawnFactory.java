package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.simulation.ActorFactory;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.content.visuals.ArrowRepresentation;
import org.codecranachan.asteroidpush.content.visuals.CircleRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.newdawn.slick.Color;

public class ActorSpawnFactory implements BehaviorFactory {
   private Socket socket;
   private NewtonianState initial_state;
   private ActorFactory factory;

   public ActorSpawnFactory(NewtonianState initial_state,
         ActorFactory factory, Socket socket) {
      this.socket = socket;
      this.initial_state = initial_state;
      this.factory = factory;
   }

   public Behavior createBehavior(Arrow offset) {
      NewtonianState converted = initial_state.transform(offset);
      return new ActorSpawnBehavior(converted, factory);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new ArrowRepresentation(initial_state.getState(), Color.red));
      reps.add(new CircleRepresentation(new Circle(initial_state.getPosition(), 0.05f), Color.red));
      return reps;
   }

   public List<Socket> getSockets() {
      List<Socket> sockets = new LinkedList<Socket>();
      sockets.add(socket);
      return sockets;
   }
}
