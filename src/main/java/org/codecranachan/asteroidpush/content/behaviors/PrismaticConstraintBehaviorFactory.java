package org.codecranachan.asteroidpush.content.behaviors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.base.simulation.PrismaticJointData;
import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.workshop.actor.Behavior;
import org.codecranachan.asteroidpush.base.workshop.assembly.BehaviorFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.content.visuals.JointRepresentation;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.Color;

public class PrismaticConstraintBehaviorFactory implements BehaviorFactory {
   List<Socket> sockets;
   PrismaticJointData data;

   public PrismaticConstraintBehaviorFactory(Socket socketA, Socket socketB,
         PrismaticJointData data) {
      sockets = new ArrayList<Socket>();
      sockets.add(socketA);
      sockets.add(socketB);
      this.data = data;
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> reps = new LinkedList<Representation>();
      reps.add(new JointRepresentation(data.getAnchorA(), data.getAnchorB(),
            Color.green));
      return reps;
   }

   public List<Socket> getSockets() {
      return sockets;
   }

   public Behavior createBehavior(Arrow offset) {
      PrismaticJointData transformed = new PrismaticJointData(data);
      transformed.transformBy(offset);
      PrismaticConstraintBehavior behavior = new PrismaticConstraintBehavior(
            transformed);
      return behavior;
   }
}
