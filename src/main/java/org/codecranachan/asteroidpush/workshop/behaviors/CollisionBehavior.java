package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.visuals.OffsetRepresentation;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.visuals.behaviors.PrimitiveRepresentation;

public class CollisionBehavior implements Behavior, InteractionHandler {
   private Hull hull;
   private RigidBody currentBody;

   public CollisionBehavior(Hull hull) {
      this.hull = hull;
      this.currentBody = null;
   }

   public Collection<Command> update(int frame) {
      return new LinkedList<Command>();
   }

   public void onDetach(RigidBody body, int index) {
      assert index == 0;
      assert body == currentBody;
      currentBody.removeHull(hull);
      currentBody = null;
   }

   public void onAttach(RigidBody body, int index) {
      assert index == 0;
      assert currentBody == null;
      currentBody = body;
      currentBody.addHull(hull, this);
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      if (currentBody != null) {
         Representation hullRepresentation = new PrimitiveRepresentation(
               hull.getShape());
         Representation hullTransform = new OffsetRepresentation(
               hullRepresentation, hull.getOffset());
         representations.add(new OffsetRepresentation(hullTransform,
               currentBody.getPosition()));
      }
      return representations;
   }
}
