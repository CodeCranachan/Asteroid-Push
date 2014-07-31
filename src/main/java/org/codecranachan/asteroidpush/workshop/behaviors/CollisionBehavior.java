package org.codecranachan.asteroidpush.workshop.behaviors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.simulation.modular.Behavior;
import org.codecranachan.asteroidpush.simulation.modular.BodyVertex;

public class CollisionBehavior implements Behavior, InteractionHandler {

   private BodyVertex node;
   private Hull hull;

   public CollisionBehavior(BodyVertex node, Hull hull) {
      this.node = node;
      this.hull = hull;
   }

   public Collection<BodyVertex> getNodes() {
      List<BodyVertex> nodes = new LinkedList<BodyVertex>();
      nodes.add(node);
      return nodes;
   }

   public Collection<Command> onAttach(RigidBody body) {
      body.addHull(hull, this);
      return new LinkedList<Command>();
   }

   public Collection<Command> onDetach(RigidBody body) {
      body.removeHull(hull);
      return new LinkedList<Command>();
   }

   public Collection<Command> onReassign(RigidBody srcBody, RigidBody dstBody) {
      srcBody.removeHull(hull);
      dstBody.addHull(hull, this);
      return new LinkedList<Command>();
   }

   public Collection<Command> update(Map<BodyVertex, RigidBody> body, int frame) {
      return new LinkedList<Command>();
   }

}
