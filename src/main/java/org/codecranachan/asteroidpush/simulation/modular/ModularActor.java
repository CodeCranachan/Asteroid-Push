package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;

public class ModularActor implements Actor {

   boolean isConstructed;
   private Map<BodyGraph, RigidBody> bodies;
   private Collection<Behavior> behaviors;

   public ModularActor() {
      isConstructed = false;
      bodies = new HashMap<BodyGraph, RigidBody>();
      behaviors = new LinkedList<Behavior>();
   }

   public void addBody(RigidBody body, BodyGraph graph) {
      assert (body != null);
      assert (graph != null);
      bodies.put(graph, body);
   }

   public void addBehavior(Behavior behavior) {
      assert (behavior != null);
      behaviors.add(behavior);
   }

   public Collection<Command> update(int frameNumber) {
      Collection<Command> allActions = new LinkedList<Command>();
      return allActions;
   }

   public void destroy() {
      // TODO Detach all behaviors from their bodies and destroy bodies
   }

}
