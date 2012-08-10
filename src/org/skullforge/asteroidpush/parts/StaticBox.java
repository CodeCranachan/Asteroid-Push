package org.skullforge.asteroidpush.parts;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class StaticBox implements Part {

   /**
    * Creates a static box with the given dimensions. The box is hollow and
    * defined by two half diagonals, each pointing to any corner of one of the
    * limiting rectangles that make up the box.
    * 
    * @param halfDiagonalA
    *           the half diagonal of the first rectangle.
    * @param halfDiagonalB
    *           the half diagonal of the second rectangle.
    */
   public StaticBox(Vec2 halfDiagonalA, Vec2 halfDiagonalB) {
      Vec2 absA = halfDiagonalA.abs();
      Vec2 absB = halfDiagonalB.abs();

      innerDiagonal = new Vec2(Math.min(absA.x, absB.x), Math.min(absA.y,
                                                                  absB.y));
      outerDiagonal = new Vec2(Math.max(absA.x, absB.x), Math.max(absA.y,
                                                                  absB.y));
      body = null;
   }

   @Override
   public void spawn(World world) {
      body = world.createBody(getBodyDef());
      AddFixturesToBody();
   }

   private BodyDef getBodyDef() {
      return new BodyDef();
   }

   private void AddFixturesToBody() {
      for (PolygonShape shape : getShapes()) {
         FixtureDef def = new FixtureDef();
         def.shape = shape;
         body.createFixture(def);
      }
   }

   private ArrayList<PolygonShape> getShapes() {
      ArrayList<PolygonShape> shapeList = new ArrayList<PolygonShape>();
      Vec2 boxes[][];

      boxes = new Vec2[][] {
            { new Vec2(innerDiagonal.x, innerDiagonal.y),
                  new Vec2(innerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y) },
            { new Vec2(-innerDiagonal.x, innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-innerDiagonal.x, innerDiagonal.y) },
            { new Vec2(outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, outerDiagonal.y),
                  new Vec2(-outerDiagonal.x, innerDiagonal.y),
                  new Vec2(outerDiagonal.x, innerDiagonal.y) },
            { new Vec2(outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -innerDiagonal.y),
                  new Vec2(-outerDiagonal.x, -outerDiagonal.y),
                  new Vec2(outerDiagonal.x, -outerDiagonal.y) } };

      for (Vec2 vertices[] : boxes) {
         PolygonShape shape;
         shape = new PolygonShape();
         shape.set(vertices, vertices.length);
         shapeList.add(shape);
      }
      return shapeList;
   }

   @Override
   public void despawn(World world) {
      world.destroyBody(body);
      body = null;
   }

   @Override
   public ArrayList<Body> getBodies() {
      ArrayList<Body> bodies = new ArrayList<Body>();
      if (body != null) {
         bodies.add(body);
      }
      return bodies;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      }

      if (!(other instanceof StaticBox)) {
         return false;
      }

      StaticBox otherBox = (StaticBox) other;

      return (innerDiagonal.equals(otherBox.innerDiagonal) && outerDiagonal
            .equals(otherBox.outerDiagonal));
   }

   private Body body;
   private Vec2 innerDiagonal;
   private Vec2 outerDiagonal;
}
