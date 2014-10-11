package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.simulation.modular.BehaviorFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.visuals.behaviors.PrimitiveRepresentation;
import org.codecranachan.asteroidpush.workshop.assembly.Socket;

public class CollisionBehaviorFactory implements BehaviorFactory {

   private Primitive shape;
   private Material material;
   private Socket socket;

   public CollisionBehaviorFactory(Primitive shape, Material material,
         Socket socket) {
      this.shape = shape;
      this.material = material;
      this.socket = socket;
   }

   public Behavior createBehavior(Arrow offset) {
      Hull hull = new Hull(offset, shape, material);
      return new CollisionBehavior(hull);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> represenations = new LinkedList<Representation>();
      represenations.add(new PrimitiveRepresentation(shape));
      return represenations;
   }

   public List<Socket> getSockets() {
      List<Socket> sockets = new LinkedList<Socket>();
      sockets.add(socket);
      return sockets;
   }

}
