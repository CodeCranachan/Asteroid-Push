package org.skullforge.asteroidpush.entities;

import static org.junit.Assert.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Test;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.entities.PassiveObject;
import org.skullforge.asteroidpush.entities.PlayingFieldBorderFactory;

public class PlayingFieldBorderFactoryTest {
   @Test
   public void testCreateEntity() {
      World world = new World(new Vec2(), true);
      PlayingFieldBorderFactory testFactory = new PlayingFieldBorderFactory(
            world);
      Entity entity = testFactory.createEntity(new Vec2());
      assertNotNull(entity);
      assertEquals(PassiveObject.class, entity.getClass());
   }
}