package org.skullforge.asteroidpush.assemblies;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.skullforge.asteroidpush.SignalController;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.EffectorData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.doodads.Effector;
import org.skullforge.asteroidpush.doodads.GlueMap;
import org.skullforge.asteroidpush.doodads.Part;
import org.skullforge.asteroidpush.doodads.ThrusterFactory;
import org.skullforge.asteroidpush.logic.Logic;

public class SpaceshipHull implements Assembly, Logic {

   public SpaceshipHull(Vec2 position, Blueprint design) {
      this.spawnPosition = new Vec2(position);
      this.design = design;
      this.bodyList = new ArrayList<Body>();
      this.effectorList = new ArrayList<Effector>();
   }

   @Override
   public void spawn(World world) {
      if (bodyList.isEmpty()) {
         GlueMap glueMap = new GlueMap();
         for (ModuleToken token : design.getTokens()) {
            for (ComponentData component : token.getData().getComponents()) {
               glueMap.putPart(new Part(token.getPlacement(), component));
            }
         }

         for (Collection<Part> partList : glueMap.getGlueGroups()) {
            bodyList.add(assembleBody(partList, world));
         }
      }
   }

   @Override
   public void despawn(World world) {
      for (Body body : bodyList) {
         world.destroyBody(body);
      }
      bodyList.clear();
      effectorList.clear();
   }

   @Override
   public Collection<Body> getBodies() {
      return bodyList;
   }

   @Override
   public Collection<Joint> getJoints() {
      return new ArrayList<Joint>();
   }

   private Body assembleBody(Collection<Part> partList, World world) {
      Body body = world.createBody(getBodyDef());
      for (Part subModule : partList) {
         Vec2 offset = calculateFixtureOffset(subModule);
         Transform transform = new Transform();
         transform.set(offset, subModule.getPlacement().getRotation()
               .getRadians());
         for (PrimitiveData subData : subModule.getComponent().getPrimitives()) {
            FixtureDef fixture = subData.getFixtureDef(transform,
                                                       standardModuleSize);
            body.createFixture(fixture);
         }

         for (EffectorData data : subModule.getComponent().getEffectors()) {
            ThrusterFactory factory = new ThrusterFactory();
            effectorList.add(factory.create(transform, body));
         }
      }
      return body;
   }

   public void update(int frameNumber, SignalController controller) {
      if (controller == null) {
         return;
      }

      for (Effector effector : effectorList) {
         effector.update(frameNumber, controller);
      }
   }

   private Vec2 calculateFixtureOffset(Part part) {
      GridVector coord = part.getPlacement().getCoordinate();
      return new Vec2(standardModuleSize * coord.getX(), standardModuleSize
            * coord.getY());
   }

   private BodyDef getBodyDef() {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(spawnPosition);
      def.angle = 0.0f;
      def.linearDamping = 0.1f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;
      return def;
   }

   private final float standardModuleSize = 0.5f;
   private ArrayList<Body> bodyList;
   private ArrayList<Effector> effectorList;
   private Vec2 spawnPosition;
   private Blueprint design;
}