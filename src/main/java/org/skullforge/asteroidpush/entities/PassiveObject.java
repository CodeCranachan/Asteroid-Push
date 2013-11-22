package org.skullforge.asteroidpush.entities;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.ui.Renderer;
import org.skullforge.asteroidpush.utils.GeometryConverter;

public class PassiveObject implements Entity {
   private Body body;
   private Player owner;

   public PassiveObject(Body body) {
      this.body = body;
      this.owner = null;
   }

   public void destroy() {
      body.getWorld().destroyBody(body);
   }

   public void render(Renderer renderer) {
      renderer.drawOutline(GeometryConverter.extractOutline(body));
   }

   public Collection<SimulatorCommand> update(int frameNumber) {
      // Passive objects do nothing special
      return null;
   }

   public Player getOwner() {
      return this.owner;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   public Vec2 getCenterOfInterest() {
      return body.getWorldCenter();
   }

   public float getRadiusOfInterest() {
      return 30.0f;
   }

}
