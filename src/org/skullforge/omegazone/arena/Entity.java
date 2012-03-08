package org.skullforge.omegazone.arena;

import org.newdawn.slick.Graphics;

public interface Entity {
  void render(Graphics g);
  void update(int delta);
}
