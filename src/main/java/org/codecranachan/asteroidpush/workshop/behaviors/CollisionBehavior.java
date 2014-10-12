package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.Collection;
import java.util.LinkedList;

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

   public Collection<Command> update(int frame) {
      return new LinkedList<Command>();
   }

   public void onDetach(RigidBody body, int index) {
      assert index == 0;
      body.removeHull(hull);
   }

   public void onAttach(RigidBody body, int index) {
      assert index == 0;
      body.addHull(hull, this);
   }
}
