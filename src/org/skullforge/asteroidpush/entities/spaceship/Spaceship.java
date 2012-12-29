package org.skullforge.asteroidpush.entities.spaceship;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Point;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.ui.Renderer;
import org.skullforge.asteroidpush.utils.GeometryConverter;

public class Spaceship implements Entity {

   ArrayList<Effector> effectors;
   ArrayList<Body> bodies;
   ArrayList<Joint> joints;
   World world;
   Player owner;

   public Spaceship(World world) {
      this.bodies = new ArrayList<Body>();
      this.effectors = new ArrayList<Effector>();
      this.joints = new ArrayList<Joint>();
      this.world = world;
      this.owner = null;
   }

   public void addJoint(Joint joint) {
      joints.add(joint);
   }

   public void addBody(Body body) {
      bodies.add(body);
   }

   public void addEffectors(Collection<Effector> effectors) {
      this.effectors.addAll(effectors);
   }

   @Override
   public void destroy() {
      for (Joint joint : joints) {
         world.destroyJoint(joint);
      }
      for (Body body : bodies) {
         world.destroyBody(body);
      }
      bodies.clear();
      effectors.clear();
      joints.clear();
      world = null;
   }

   @Override
   public void render(Renderer renderer) {
      for (Body body : bodies) {
         renderer.drawOutline(GeometryConverter.extractOutline(body));
      }
      for (Joint joint : joints) {
         Vec2 start = new Vec2();
         Vec2 end = new Vec2();
         joint.getAnchorA(start);
         joint.getAnchorB(end);
         renderer.drawLine(new Point(start.x, start.y), new Point(end.x, end.y), 1.5f, Color.pink);
      }
   }

   @Override
   public Collection<SimulatorCommand> update(int frameNumber) {
      Collection<SimulatorCommand> commands = new ArrayList<SimulatorCommand>();
      Collection<SimulatorCommand> effectorCommands;

      for (Effector effector : effectors) {
         if (owner != null) {
            effectorCommands = effector.update(frameNumber, owner.getController());
         } else {
            effectorCommands = effector.update(frameNumber, null);
         }
         if (effectorCommands!=null) {
            commands.addAll(effectorCommands);
         }
      }
      
      return commands;
   }

   @Override
   public Player getOwner() {
      return this.owner;
   }

   @Override
   public void setOwner(Player owner) {
      this.owner = owner;
   }

   @Override
   public Vec2 getCenterOfInterest() {
      Vec2 position = new Vec2(0.0f, 0.0f);
      float numberOfBodies = 0.0f;
      for (Body body : bodies) {
         position.addLocal(body.getWorldCenter());
         ++numberOfBodies;
      }
      return position.mul(1.0f / numberOfBodies);
   }

   @Override
   public float getRadiusOfInterest() {
      return 35.0f;
   }

}
