package org.skullforge.asteroidpush.doodads;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PlayingFieldBorderFactory implements EntityFactory {

   final private float borderThickness = 10.0f;

   private float fieldHeight;

   private float fieldWidth;

   private Vec2 innerDiagonal;

   private Vec2 outerDiagonal;

   final private World world;

   public PlayingFieldBorderFactory(World world) {
      this.world = world;
      fieldWidth = 256.0f;
      fieldHeight = 256.0f;
   }

   @Override
   public Entity createEntity(Vec2 location) {
      Vec2 border = new Vec2(borderThickness, borderThickness);
      innerDiagonal = new Vec2(fieldWidth / 2.0f, fieldHeight / 2.0f);
      outerDiagonal = innerDiagonal.add(border);

      Body body = world.createBody(getBodyDef());
      for (PolygonShape shape : getShapes()) {
         FixtureDef def = new FixtureDef();
         def.shape = shape;
         body.createFixture(def);
      }

      Entity entity = new PassiveObject(body, world);
      return entity;
   }

   private BodyDef getBodyDef() {
      return new BodyDef();
   }

   private ArrayList<PolygonShape> getShapes() {
      ArrayList<PolygonShape> shapeList = new ArrayList<PolygonShape>();
      Vec2 boxes[][];

      boxes = new Vec2[][] {
            {
                  new Vec2(innerDiagonal.x, innerDiagonal.y),
                  new Vec2(innerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y)
            },
            {
                  new Vec2(-innerDiagonal.x, innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-innerDiagonal.x, -innerDiagonal.y)
            },
            {
                  new Vec2(outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y)
            },
            {
                  new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -outerDiagonal.y),
                  new Vec2(outerDiagonal.x, -outerDiagonal.y)
            }
      };

      for (Vec2 vertices[] : boxes) {
         PolygonShape shape;
         shape = new PolygonShape();
         shape.set(vertices, vertices.length);
         shapeList.add(shape);
      }
      return shapeList;
   }

   public void setParameters(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }
}
