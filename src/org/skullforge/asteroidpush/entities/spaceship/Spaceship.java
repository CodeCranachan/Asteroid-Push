package org.skullforge.asteroidpush.entities.spaceship;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.ui.Renderer;
import org.skullforge.asteroidpush.utils.GeometryConverter;

public class Spaceship implements Entity {

   ArrayList<Body> bodies;
   World world;
   Player owner;
   
   public Spaceship(World world) {
      this.bodies = new ArrayList<Body>();
      this.world = world;
      this.owner = null;
   }

   public void addBody(Body body) {
      bodies.add(body);
   }

   @Override
   public void destroy() {
      for (Body body : bodies) {
         world.destroyBody(body);
      }
      bodies.clear();
   }

   @Override
   public void render(Renderer renderer) {
      for (Body body : bodies) {
         renderer.drawOutline(GeometryConverter.extractOutline(body));
      }
   }

   @Override
   public void update(int frameNumber) {
      // TODO Auto-generated method stub
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
      Vec2 position = new Vec2(0.0f, 0.0f);
      float numberOfBodies = 0.0f;
      for (Body body : bodies) {
         position.addLocal(body.getWorldCenter());
         ++numberOfBodies;
      }
      return position.mul(1.0f / numberOfBodies);
   }

   @Override
   public float getRadiusOfInterest() {
      return 35.0f;
   }

}
