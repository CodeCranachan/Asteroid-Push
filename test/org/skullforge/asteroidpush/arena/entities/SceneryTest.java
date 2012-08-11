package org.skullforge.asteroidpush.arena.entities;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SceneryTest {
   Scenery testScenery;
   World testWorld;

   @Before
   public void setUp() throws Exception {
      testScenery = new Scenery();
      Vec2 gravity = new Vec2(0.0f, 0.0f);
      testWorld = new World(gravity, true);
   }

   @Test
   public void testSpawning() {
      Vec2 position = new Vec2(0.0f, 0.0f);
      testScenery.spawn(testWorld, position);
      assertEquals(position, testScenery.getPosition());
   }
}
