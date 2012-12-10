package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.ui.Renderer;
import org.skullforge.asteroidpush.utils.GeometryConverter;

public class PassiveObject implements Entity {
   private World world;
   private Body body;
   private Player owner;

   public PassiveObject(Body body, World world) {
      this.world = world;
      this.body = body;
      this.owner = null;
   }

   @Override
   public void destroy() {
      world.destroyBody(body);
   }

   @Override
   public void render(Renderer renderer) {
      renderer.drawOutline(GeometryConverter.extractOutline(body));
   }

   @Override
   public void update(int frameNumber) {
      // Passive objects do nothing special
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
      return body.getWorldCenter();
   }

   @Override
   public float getRadiusOfInterest() {
      return 30.0f;
   }

}
