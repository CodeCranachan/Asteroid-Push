package org.skullforge.asteroidpush.parts;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.parts.StaticBox;

public class StaticBoxTest {
   StaticBox testBox;
   World testWorld;
   Vec2 innerDiagonal = new Vec2(100.0f, 50.0f);
   Vec2 outerDiagonal = new Vec2(110.0f, 60.0f);

   @Before
   public void setUp() throws Exception {
      testWorld = new World(new Vec2(), true);
      innerDiagonal = new Vec2(100.0f, 50.0f);
      outerDiagonal = new Vec2(110.0f, 60.0f);
      testBox = new StaticBox(innerDiagonal, outerDiagonal);
   }

   @Test
   public void testStaticBox() {
      ArrayList<Body> bodies = testBox.getBodies();
      assertEquals(0, bodies.size());
   }

   @Test
   public void testSpawn() {
      testBox.spawn(testWorld);
      ArrayList<Body> bodies = testBox.getBodies();
      assertEquals(1, bodies.size());

      Body body = bodies.get(0);
      assertEquals(BodyType.STATIC, body.getType());
      assertEquals(0.0f, body.getAngle(), 0.0001f);
      assertEquals(new Vec2(0.0f, 0.0f), body.getPosition());

      for (Vec2 point : getPointsExpectedToCollide()) {
         assertTrue(point.toString(), checkCollision(body, point));
      }

      for (Vec2 point : getPointsExpectedToMiss()) {
         assertFalse(point.toString(), checkCollision(body, point));
      }
   }

   private ArrayList<Vec2> getPointsExpectedToCollide() {
      Vec2 delta = new Vec2(0.5f, 0.5f);
      Vec2 innerCollisionA = innerDiagonal.add(delta);
      Vec2 outerCollisionA = outerDiagonal.sub(delta);
      Vec2 innerCollisionB = new Vec2(innerCollisionA.x, -innerCollisionA.y);
      Vec2 outerCollisionB = new Vec2(outerCollisionA.x, -outerCollisionA.y);

      ArrayList<Vec2> pointsExpectedToCollide = new ArrayList<Vec2>();
      pointsExpectedToCollide.add(innerCollisionA);
      pointsExpectedToCollide.add(innerCollisionA.negate());
      pointsExpectedToCollide.add(outerCollisionA);
      pointsExpectedToCollide.add(outerCollisionA.negate());
      pointsExpectedToCollide.add(innerCollisionB);
      pointsExpectedToCollide.add(innerCollisionB.negate());
      pointsExpectedToCollide.add(outerCollisionB);
      pointsExpectedToCollide.add(outerCollisionB.negate());
      return pointsExpectedToCollide;
   }

   private ArrayList<Vec2> getPointsExpectedToMiss() {
      Vec2 delta = new Vec2(0.5f, 0.5f);
      Vec2 innerMissA = innerDiagonal.sub(delta);
      Vec2 outerMissA = outerDiagonal.add(delta);
      Vec2 innerMissB = new Vec2(innerMissA.x, -innerMissA.y);
      Vec2 outerMissB = new Vec2(outerMissA.x, -outerMissA.y);

      ArrayList<Vec2> pointsExpectedToMiss = new ArrayList<Vec2>();
      pointsExpectedToMiss.add(innerMissA);
      pointsExpectedToMiss.add(innerMissA.negate());
      pointsExpectedToMiss.add(outerMissA);
      pointsExpectedToMiss.add(outerMissA.negate());
      pointsExpectedToMiss.add(innerMissB);
      pointsExpectedToMiss.add(innerMissB.negate());
      pointsExpectedToMiss.add(outerMissB);
      pointsExpectedToMiss.add(outerMissB.negate());
      return pointsExpectedToMiss;
   }

   private boolean checkCollision(Body body, Vec2 point) {
      Fixture nextFixture = body.getFixtureList();
      while (nextFixture != null) {
         Transform transform = new Transform();
         transform.setIdentity();
         Shape shape = nextFixture.getShape();
         if (shape.testPoint(transform, point)) {
            return true;
         }
         nextFixture = nextFixture.getNext();
      }
      return false;
   }

   @Test
   public void testDespawn() {
      testBox.spawn(testWorld);

      testBox.despawn(testWorld);
      ArrayList<Body> bodies = testBox.getBodies();
      assertEquals(0, bodies.size());
   }
}
