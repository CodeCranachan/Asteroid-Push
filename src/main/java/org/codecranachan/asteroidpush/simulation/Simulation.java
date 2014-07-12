package org.codecranachan.asteroidpush.simulation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import org.codecranachan.asteroidpush.simulation.command.Command;
import org.codecranachan.asteroidpush.visuals.Representable;
import org.codecranachan.asteroidpush.visuals.Representation;

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

   public Representation getRepresentation() {
      // TODO implement a representation
      return null;
   }
}
