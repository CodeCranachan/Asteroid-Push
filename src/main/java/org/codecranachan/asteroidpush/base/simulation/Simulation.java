package org.codecranachan.asteroidpush.base.simulation;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public class Simulation implements Representable {
   private PhysicsEngine engine;
   private int currentFrameNumber;
   private Collection<Actor> actors;
   private Stack<Command> commands;

   public Simulation(PhysicsEngine engine) {
      this.engine = engine;
      this.actors = new HashSet<Actor>();
      this.commands = new Stack<Command>();
   }

   public int getCurrentFrameNumber() {
      return currentFrameNumber;
   }

   public void clear() {
      currentFrameNumber = 0;
      commands.clear();
      for (Actor nextActor : actors) {
         nextActor.destroy();
      }
      actors.clear();
   }

   public RigidBodyFactory getBodyFactory() {
      return engine.getBodyFactory();
   }

   public void addActor(Actor actor) {
      actors.add(actor);
   }

   public void removeActor(Actor actor) {
      actor.destroy();
      actors.remove(actor);
   }

   public void stepToFrame(int targetFrameNumber) {
      while (currentFrameNumber < targetFrameNumber) {
         computeNextFrame();
      }
   }

   private void computeNextFrame() {
      ++currentFrameNumber;
      updateActors();
      executeCommands();
      engine.stepWorld();
   }

   private void updateActors() {
      for (Actor nextActor : actors) {
         commands.addAll(nextActor.update(currentFrameNumber));
      }
   }

   private void executeCommands() {
      while (!commands.empty()) {
         commands.pop().execute(this);
      }
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> simulationRepresentations = new LinkedList<Representation>();
      for (Actor nextActor : actors) {
         simulationRepresentations.addAll(nextActor.getRepresentations());
      }
      return simulationRepresentations;
   }
}
