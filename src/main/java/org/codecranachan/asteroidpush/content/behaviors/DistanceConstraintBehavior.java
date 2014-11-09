package org.codecranachan.asteroidpush.content.behaviors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.DistanceJointData;
import org.codecranachan.asteroidpush.base.simulation.DynamicJoint;
import org.codecranachan.asteroidpush.base.simulation.DynamicJointFactory;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;

public class DistanceConstraintBehavior implements Behavior {
   DistanceJointData data;
   List<RigidBody> bodies;
   DynamicJoint joint;

   public DistanceConstraintBehavior(DistanceJointData data) {
      this.data = data;
      bodies = new ArrayList<RigidBody>(2);
      bodies.add(null);
      bodies.add(null);
   }

   public Collection<Command> update(int frame) {
      return new LinkedList<Command>();
   }

   public void onDetach(RigidBody body, int index) {
      assert index == 0 || index == 1;
      bodies.set(index, null);
      if (joint != null) {
         joint.destroy();
         joint = null;
      }
   }

   public void onAttach(RigidBody body, int index) {
      assert index == 0 || index == 1;
      bodies.set(index, body);
      if (bodies.get(0) != null && bodies.get(1) != null) {
         DynamicJointFactory factory = body.getJointFactory();
         joint = factory
               .createDistanceJoint(bodies.get(0), bodies.get(1), data);
      }
   }

   public Collection<Representation> getRepresentations() {
      return new LinkedList<Representation>();
   }

   public void setController(Controller controller, int index) {
      // This behavior is not controllable
   }

}
