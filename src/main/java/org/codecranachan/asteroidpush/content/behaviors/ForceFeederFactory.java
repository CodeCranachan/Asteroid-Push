package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.content.visuals.ArrowRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Force;
import org.newdawn.slick.Color;

public class ForceFeederFactory implements BehaviorFactory {

   private Force force;
   private Socket socket;

   /**
    * Creates the ForceFeederFactory.
    * 
    * @param force
    *           defines the offset, direction and magnitude of the force that
    *           the Behavior will apply to its attached body when activated.
    * @param socket
    *           the socket coordinates where the created behavior will be
    *           attached to.
    */
   public ForceFeederFactory(Force force, Socket socket) {
      this.force = force;
      this.socket = socket;
   }

   public Behavior createBehavior(Arrow offset) {
      Force transformed = force.transformBy(offset);
      return new ForceFeederBehavior(transformed);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new ArrowRepresentation(force.asArrow(), Color.red));
      return reps;
   }

   public List<Socket> getSockets() {
      List<Socket> sockets = new LinkedList<Socket>();
      sockets.add(socket);
      return sockets;
   }

}
