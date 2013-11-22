package org.skullforge.asteroidpush.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Test;
import org.skullforge.asteroidpush.entities.AsteroidFactory;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.entities.PassiveObject;

public class AsteroidFactoryTest {
   @Test
   public void testCreateEntity() {
      World world = new World(new Vec2());
      AsteroidFactory testFactory = new AsteroidFactory(world);
      Entity entity = testFactory.createEntity(new Vec2());
      assertNotNull(entity);
      assertEquals(PassiveObject.class, entity.getClass());
   }
}