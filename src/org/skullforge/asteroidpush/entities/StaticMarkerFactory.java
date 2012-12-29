package org.skullforge.asteroidpush.entities;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class StaticMarkerFactory implements EntityFactory {
   final World world;

   public StaticMarkerFactory(World world) {
      this.world = world;
   }

   @Override
   public Entity createEntity(Vec2 location) {
      StaticMarker entity = new StaticMarker(null, world, location);
      return entity;
   }

}
