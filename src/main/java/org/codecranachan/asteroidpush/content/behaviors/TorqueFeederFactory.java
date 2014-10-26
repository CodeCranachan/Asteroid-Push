package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.content.visuals.SpinRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;

public class TorqueFeederFactory implements BehaviorFactory {
   private Socket socket;
   private float torque;

   public TorqueFeederFactory(float torque, Socket socket) {
      this.socket = socket;
      this.torque = torque;
   }

   public Behavior createBehavior(Arrow offset) {
      return new TorqueFeederBehavior(torque);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new SpinRepresentation(new Arrow(), Color.red));
      return reps;
   }

   public List<Socket> getSockets() {
      List<Socket> sockets = new LinkedList<Socket>();
      sockets.add(socket);
      return sockets;
   }
}
