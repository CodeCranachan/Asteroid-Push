package org.skullforge.asteroidpush.entities.spaceship;

import java.util.Collection;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.entities.EntityFactory;

public class SpaceshipFactory implements EntityFactory {

   private final float standardModuleSize = 0.5f;
   private Blueprint design;
   private World world;

   public SpaceshipFactory(Blueprint design, World world) {
      this.design = design;
      this.world = world;
   }

   @Override
   public Entity createEntity(Vec2 position) {
      Spaceship ship = new Spaceship(world);

      GlueMap glueMap = new GlueMap();
      for (ModuleToken token : design.getTokens()) {
         for (ComponentData component : token.getData().getComponents()) {
            glueMap.putPart(new Part(token.getPlacement(), component));
         }
      }

      for (Collection<Part> partList : glueMap.getGlueGroups()) {
         ship.addBody(assembleBody(partList, position));
      }

      return ship;
   }

   private Vec2 calculateFixtureOffset(Part part) {
      GridVector coord = part.getPlacement().getCoordinate();
      return new Vec2(standardModuleSize * coord.getX(), standardModuleSize
            * coord.getY());
   }

   private BodyDef getBodyDef(Vec2 position) {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(position);
      def.angle = 0.0f;
      def.linearDamping = 0.05f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;
      return def;
   }

   private Body assembleBody(Collection<Part> partList, Vec2 position) {
      Body body = world.createBody(getBodyDef(position));
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
      }
      return body;
   }
}
