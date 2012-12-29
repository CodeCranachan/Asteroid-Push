package org.skullforge.asteroidpush.entities;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Point;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.ui.Renderer;

public class StaticMarker implements Entity {
   private Player owner;
   private World world;
   private Vec2 location;

   public StaticMarker(Body body, World world, Vec2 location) {
      this.world = world;
      this.owner = null;
      this.location = location;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void render(Renderer renderer) {
      renderer.drawLine(new Point(location.x - 1.0f, location.y - 1.0f),
                        new Point(location.x + 1.0f, location.y + 1.0f),
                        2.0f,
                        Color.red);
      renderer.drawLine(new Point(location.x + 1.0f, location.y - 1.0f),
                        new Point(location.x - 1.0f, location.y + 1.0f),
                        2.0f,
                        Color.red);
   }

   @Override
   public Collection<SimulatorCommand> update(int frameNumber) {
      // Markers do nothing special
      return null;
   }

   @Override
   public Player getOwner() {
      return this.owner;
   }

   @Override
   public void setOwner(Player owner) {
      this.owner = owner;
   }

   @Override
   public Vec2 getCenterOfInterest() {
      return location;
   }

   @Override
   public float getRadiusOfInterest() {
      return 30.0f;
   }

}
