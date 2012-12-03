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
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.SubModule;
import org.skullforge.asteroidpush.designer.modules.data.EffectorFactory;
import org.skullforge.asteroidpush.designer.modules.data.SubPartData;
import org.skullforge.asteroidpush.doodads.Effector;
import org.skullforge.asteroidpush.logic.Logic;

public class SpaceshipHull implements Assembly, Logic {

   public SpaceshipHull(Vec2 position, ShipDesign design) {
      this.spawnPosition = new Vec2(position);
      this.design = design;
      this.bodyList = new ArrayList<Body>();
      this.effectorList = new ArrayList<Effector>();
   }

   @Override
   public void spawn(World world) {
      if (bodyList.isEmpty()) {
         for (Collection<SubModule> subModuleList : design.getBodyGroups()) {
            bodyList.add(assembleBody(subModuleList, world));
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

   private Body assembleBody(Collection<SubModule> partList, World world) {
      Body body = world.createBody(getBodyDef());
      for (SubModule subModule : partList) {
         Vec2 offset = calculateFixtureOffset(subModule);
         Transform transform = new Transform();
         transform.set(offset, subModule.getPlace().getRotation().getRadians());
         for (SubPartData subData : subModule.getData().getSubParts()) {
            FixtureDef fixture = subData.getFixtureDef(transform,
                                                       standardModuleSize);
            body.createFixture(fixture);
         }

         for (EffectorFactory factory : subModule.getData()
               .getEffectorFactories()) {
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

   private Vec2 calculateFixtureOffset(SubModule subModule) {
      return new Vec2(standardModuleSize
            * subModule.getPlace().getCoordinate().getX(), standardModuleSize
            * subModule.getPlace().getCoordinate().getY());
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
   private ShipDesign design;
}