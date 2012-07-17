package org.skullforge.asteroidpush.arena;

import org.skullforge.asteroidpush.arena.Viewport;

public interface Entity {
  void render(Viewport view);
  void update(int delta);
}
