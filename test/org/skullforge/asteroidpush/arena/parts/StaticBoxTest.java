package org.skullforge.asteroidpush.arena.parts;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;

public class StaticBoxTest {
   StaticBox testBox;
   World testWorld;

   @Before
   public void setUp() throws Exception {
      testWorld = new World(new Vec2(), true);
      Vec2 innerDiagonal = new Vec2(100.0f, 50.0f);
      Vec2 outerDiagnonal = new Vec2(110.0f, 60.0f);
      testBox = new StaticBox(innerDiagonal, outerDiagnonal);
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
   }

   @Test
   public void testDespawn() {
      testBox.spawn(testWorld);

      testBox.despawn(testWorld);
      ArrayList<Body> bodies = testBox.getBodies();
      assertEquals(0, bodies.size());
   }
}
