//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.entities.spaceship;

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.Player;
import org.codecranachan.asteroidpush.SimulatorCommand;
import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.ui.Renderer;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Point;

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

   public Player getOwner() {
      return this.owner;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   public Vec2 getCenterOfInterest() {
      Vec2 position = new Vec2(0.0f, 0.0f);
      float numberOfBodies = 0.0f;
      for (Body body : bodies) {
         position.addLocal(body.getWorldCenter());
         ++numberOfBodies;
      }
      return position.mul(1.0f / numberOfBodies);
   }

   public float getRadiusOfInterest() {
      return 35.0f;
   }

}
