package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;

public class CollisionBehavior implements Behavior, InteractionHandler {
   private Hull hull;

   public CollisionBehavior(Hull hull) {
      this.hull = hull;
   }

   public Collection<Command> update(Vector<RigidBody> bodies, int frame) {
      return new LinkedList<Command>();
   }

   public Collection<Command> onDetach(Vector<RigidBody> bodies, int frame) {
      assert bodies.size() == 1;
      bodies.firstElement().addHull(hull, this);
      return new LinkedList<Command>();
   }

   public Collection<Command> onAttach(Vector<RigidBody> bodies, int frame) {
      assert bodies.size() == 1;
      bodies.firstElement().removeHull(hull);
      return new LinkedList<Command>();
   }
}
