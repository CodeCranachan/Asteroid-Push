package org.skullforge.asteroidpush.assemblies;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.assemblies.Debris;
import org.skullforge.asteroidpush.testutils.GeometryVerifier;

public class DebrisTest {
   Debris testDebris;
   World testWorld;

   @Before
   public void setUp() throws Exception {
      testDebris = new Debris(new Vec2());
      Vec2 gravity = new Vec2();
      testWorld = new World(gravity, true);
   }

   @Test
   public void testConstruction() {
      ArrayList<Body> bodies = testDebris.getBodies();
      assertEquals(0, bodies.size());
   }

   @Test
   public void testSpawn() {
      testDebris.spawn(testWorld);
      ArrayList<Body> bodies = testDebris.getBodies();
      assertEquals(1, bodies.size());
      Body body = bodies.get(0);

      // check that nothing is done when a spawned part is spawned is spawned
      // again
      testDebris.spawn(testWorld);
      ArrayList<Body> newBodies = testDebris.getBodies();
      assertEquals(1, newBodies.size());
      Body newBody = newBodies.get(0);
      assertSame(body, newBody);
   }

   @Test
   public void testDespawn() {
      testDebris.spawn(testWorld);

      testDebris.despawn(testWorld);
      ArrayList<Body> bodies = testDebris.getBodies();
      assertEquals(0, bodies.size());

      // Check that there will be no crash when a despawned part is despawned
      // again
      testDebris.despawn(testWorld);
   }

   @Test
   public void testBodyValidity() {
      testDebris.spawn(testWorld);

      ArrayList<Body> bodies = testDebris.getBodies();
      for (Body body : bodies) {
         assertTrue(GeometryVerifier.IsBodySane(body));
      }
   }
}
