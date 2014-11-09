package org.codecranachan.asteroidpush.content.behaviors;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.simulation.PrismaticJointData;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.content.visuals.JointRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;

public class PrismaticConstraintBehaviorFactory implements BehaviorFactory {
   PrismaticJointData data;

   public PrismaticConstraintBehaviorFactory(PrismaticJointData data) {
      this.data = data;
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new JointRepresentation(data.getAnchorA(), data.getAnchorB(),
            Color.green));
      return reps;
   }

   public Behavior createBehavior(Arrow offset) {
      PrismaticJointData transformed = new PrismaticJointData(data);
      transformed.transformBy(offset);
      PrismaticConstraintBehavior behavior = new PrismaticConstraintBehavior(
            transformed);
      return behavior;
   }
}
