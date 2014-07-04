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
import java.util.LinkedList;

import org.codecranachan.asteroidpush.designer.Blueprint;
import org.codecranachan.asteroidpush.designer.ModuleToken;
import org.codecranachan.asteroidpush.designer.data.ComponentData;
import org.codecranachan.asteroidpush.designer.data.EffectorData;
import org.codecranachan.asteroidpush.designer.data.JointData;
import org.codecranachan.asteroidpush.designer.data.PrimitiveData;
import org.codecranachan.asteroidpush.designer.grid.GridVector;
import org.codecranachan.asteroidpush.designer.grid.Placement;
import org.codecranachan.asteroidpush.designer.grid.Rotation;
import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.EntityFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.JointDef;

public class SpaceshipFactory implements EntityFactory {

   private final float standardModuleSize = 0.5f;
   private Blueprint design;
   private World world;

   public SpaceshipFactory(Blueprint design, World world) {
      this.design = design;
      this.world = world;
   }

   public Entity createEntity(Arrow position) {
      return null;
   }

   public Entity createEntity(Vec2 position) {
      Spaceship ship = new Spaceship(world);

      ArrayList<Link> shipLinks = new ArrayList<Link>();

      GlueMap glueMap = new GlueMap();
      for (ModuleToken token : design.getTokens()) {
         ArrayList<Link> links = new ArrayList<Link>();
         for (JointData joint : token.getData().getJoints()) {
            links.add(new Link(joint));
         }

         for (ComponentData component : token.getData().getComponents()) {
            Part part = new Part(token.getPlacement(), component);
            for (Link link : links) {
               link.match(part);
            }
            glueMap.putPart(part);
         }
         shipLinks.addAll(links);
      }

      Collection<Collection<Part>> partLists = glueMap.getGlueGroups();

      for (Collection<Part> partList : partLists) {
         Body body = assembleBody(partList, position);
         for (Part part : partList) {
            part.setBody(body);
         }
         Collection<Effector> effectors = assembleEffectors(partList, body);
         ship.addBody(body);
         ship.addEffectors(effectors);
      }

      for (Link link : shipLinks) {
         JointDef def = link.getJointDef(standardModuleSize);
         if (def != null) {
            Joint joint = world.createJoint(def);
            ship.addJoint(joint);
         }
      }

      return ship;
   }

   private Transform calculateFixtureTransform(Placement placement) {
      GridVector coord = placement.getCoordinate();
      Rotation rot = placement.getRotation();

      Vec2 offset = new Vec2(coord.getX(), coord.getY());
      offset.mulLocal(standardModuleSize);

      Transform transform = new Transform();
      transform.set(offset, rot.getRadians());
      return transform;
   }

   private BodyDef getBodyDef(Vec2 position) {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(position);
      def.angle = MathUtils.HALF_PI;
      def.linearDamping = 0.05f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;
      return def;
   }

   Collection<Effector> assembleEffectors(Collection<Part> partList, Body body) {
      LinkedList<Effector> effectors = new LinkedList<Effector>();
      for (Part part : partList) {
         Transform transform = calculateFixtureTransform(part.getPlacement());
         ComponentData component = part.getComponent();
         for (EffectorData data : component.getEffectors()) {
            Effector effector = data.createEffector(standardModuleSize,
                                                    transform,
                                                    body);
            effectors.add(effector);
         }
      }
      return effectors;
   }

   private Body assembleBody(Collection<Part> partList, Vec2 position) {
      Body body = world.createBody(getBodyDef(position));
      for (Part subModule : partList) {
         Transform transform = calculateFixtureTransform(subModule
               .getPlacement());
         for (PrimitiveData subData : subModule.getComponent().getPrimitives()) {
            FixtureDef fixture = subData.getFixtureDef(transform,
                                                       standardModuleSize);
            body.createFixture(fixture);
         }
      }
      return body;
   }
}
