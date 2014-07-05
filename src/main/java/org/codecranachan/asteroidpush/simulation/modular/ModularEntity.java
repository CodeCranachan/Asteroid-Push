package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.codecranachan.asteroidpush.Player;
import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.ui.Renderer;
import org.jbox2d.common.Vec2;

public class ModularEntity implements Entity {

   private Map<BodyGraph, RigidBody> bodies;
   private Collection<Behavior> behaviors;
   private Collection<Constraint> constraints;

   public ModularEntity() {
      bodies = new HashMap<BodyGraph, RigidBody>();
      behaviors = new LinkedList<Behavior>();
      constraints = new LinkedList<Constraint>();

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

   public void addConstraint(Constraint constraint) {
      assert (constraint != null);
      constraints.add(constraint);
   }

   public void destroy() {
      // TODO Auto-generated method stub

   }

   public void render(Renderer renderer) {
      // TODO Auto-generated method stub

   }

   public Collection<SimulatorCommand> update(int frameNumber) {
      Collection<SimulatorCommand> allActions = new LinkedList<SimulatorCommand>();
      for (Behavior behavior : behaviors) {
         Collection<SimulatorCommand> actions = behavior
               .update(getBodyOf(behavior.getNode()), frameNumber);
         allActions.addAll(actions);
      }

      for (Constraint constraint : constraints) {
         Collection<SimulatorCommand> actions = constraint
               .update(getBodyOf(constraint.getNodeA()),
                       getBodyOf(constraint.getNodeB()),
                       frameNumber);
         allActions.addAll(actions);
      }

      return allActions;
   }

   public Player getOwner() {
      // TODO Auto-generated method stub
      return null;
   }

   public void setOwner(Player owner) {
      // TODO Auto-generated method stub

   }

   public Vec2 getCenterOfInterest() {
      // TODO Auto-generated method stub
      return null;
   }

   public float getRadiusOfInterest() {
      // TODO Auto-generated method stub
      return 0;
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

}
