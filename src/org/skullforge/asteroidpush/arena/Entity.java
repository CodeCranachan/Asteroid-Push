package org.skullforge.asteroidpush.arena;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.arena.Viewport;

public interface Entity {
  void spawn(World world, Vec2 position);
  void render(Viewport view);
  void update(int delta);
  Vec2 getPosition();
}
