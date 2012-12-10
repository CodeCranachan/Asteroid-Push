package org.skullforge.asteroidpush.entities;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ui.Renderer;

public interface Entity {
   void destroy();

   void render(Renderer renderer);

   void update(int frameNumber);

   Player getOwner();

   void setOwner(Player owner);

   Vec2 getCenterOfInterest();

   float getRadiusOfInterest();
}
