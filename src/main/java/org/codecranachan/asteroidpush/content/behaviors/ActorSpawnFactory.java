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
import org.codecranachan.asteroidpush.utils.Velocity;
import org.newdawn.slick.Color;

public class ActorSpawnFactory implements BehaviorFactory {
   private Socket socket;
   private Arrow spawn_offset;
   private Velocity spawn_velocity;
   private ActorFactory factory;

   public ActorSpawnFactory(Arrow offset, Velocity velocity,
         ActorFactory factory, Socket socket) {
      this.socket = socket;
      this.spawn_offset = offset;
      this.spawn_velocity = velocity;
      this.factory = factory;
   }

   public Behavior createBehavior(Arrow offset) {
      return new ActorSpawnBehavior(new Arrow(offset.getTail()
            .add(spawn_offset.getTail()), offset.getAngle()
            + spawn_offset.getAngle()), spawn_velocity.rotate(spawn_offset
            .getAngle() + offset.getAngle()), factory);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new ArrowRepresentation(spawn_offset, Color.red));
      reps.add(new CircleRepresentation(new Arrow(spawn_offset.getTail(),
            spawn_offset.getAngle(), 0.05f), Color.red));
      return reps;
   }

   public List<Socket> getSockets() {
      List<Socket> sockets = new LinkedList<Socket>();
      sockets.add(socket);
      return sockets;
   }
}
