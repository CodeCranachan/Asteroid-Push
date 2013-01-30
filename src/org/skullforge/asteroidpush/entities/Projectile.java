package org.skullforge.asteroidpush.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.skullforge.asteroidpush.DestroyEntityCommand;
import org.skullforge.asteroidpush.ImpactListener;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.SimulatorCommand;
import org.skullforge.asteroidpush.ui.Renderer;
import org.skullforge.asteroidpush.utils.GeometryConverter;

public class Projectile implements Entity, ImpactListener {
   private Body body;
   private Player owner;
   private int timeToLive;
   private boolean impactDetected;

   public Projectile(Body body, int timeToLive) {
      this.body = body;
      this.owner = null;
      this.timeToLive = timeToLive;
      this.impactDetected = false;
      
      Fixture fixture = body.getFixtureList();
      while (fixture != null) {
         fixture.setUserData(this);
         fixture = fixture.getNext();
      }
   }

   @Override
   public void destroy() {
      body.getWorld().destroyBody(body);
   }

   @Override
   public void render(Renderer renderer) {
      renderer.drawOutline(GeometryConverter.extractOutline(body));
   }

   @Override
   public Collection<SimulatorCommand> update(int frameNumber) {
      if (timeToLive <= 0 || impactDetected) {
         ArrayList<SimulatorCommand> commands = new ArrayList<SimulatorCommand>();
         commands.add(new DestroyEntityCommand(this));
         return commands;
      } else {
         --timeToLive;
         return null;
      }
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

   @Override
   public void handleImpact() {
      impactDetected = true;
   }
}
