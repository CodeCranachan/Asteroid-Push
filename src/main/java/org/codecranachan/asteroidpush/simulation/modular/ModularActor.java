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
      
      if (!isConstructed) {
         Collection<Command> actions = behavior
               .attach(getNodeAssociation(behavior), frameNumber);
         allActions.addAll(actions);
      }

      for (Behavior behavior : behaviors) {
         Collection<Command> actions = behavior
               .update(getNodeAssociation(behavior), frameNumber);
         allActions.addAll(actions);
      }

      return allActions;
   }

   private Map<BodyVertex, RigidBody> getNodeAssociation(Behavior behavior) {
      // TODO Could be optimized by just returning the overall association
      // between behaviors and bodies (e.g. a Map)
      assert (behavior != null);
      Map<BodyVertex, RigidBody> nodeAssociation = new HashMap<BodyVertex, RigidBody>();
      for (BodyVertex vertex : behavior.getNodes()) {
         nodeAssociation.put(vertex, getBodyOf(vertex));
      }
      return nodeAssociation;
   }

   private RigidBody getBodyOf(BodyVertex vertex) {
      // TODO Could be optimized by keeping an association between
      // vertices and bodies (e.g. a Map)
      assert (vertex != null);
      for (Entry<BodyGraph, RigidBody> entry : bodies.entrySet()) {
         if (entry.getKey().containsVertex(vertex)) {
            return entry.getValue();
         }
      }
      return null;
   }

   public void destroy() {
      // TODO Detach all behaviors from their bodies and destroy bodies
   }

}
