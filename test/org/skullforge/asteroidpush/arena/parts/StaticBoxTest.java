package org.skullforge.asteroidpush.arena.parts;

import static org.junit.Assert.*;

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
      int expectedNumberOfBodies = 0;
      Body bodies[];
      bodies = testBox.getBodies();
      assertEquals(expectedNumberOfBodies, bodies.length);
   }
   
   @Test
   public void testSpawn() {
      int expectedNumberOfBodies = 1;
      testBox.spawn(testWorld);
      Body bodies[] = testBox.getBodies();
      assertEquals(expectedNumberOfBodies, bodies.length);
   }

   @Test
   public void testDespawn() {
      testBox.spawn(testWorld);
      
      int expectedNumberOfBodies = 0;
      testBox.despawn(testWorld);
      Body bodies[] = testBox.getBodies();
      assertEquals(expectedNumberOfBodies, bodies.length);
   }
}
