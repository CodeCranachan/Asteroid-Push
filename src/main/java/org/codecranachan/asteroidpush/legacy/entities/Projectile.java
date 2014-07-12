//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.legacy.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.codecranachan.asteroidpush.legacy.DestroyEntityCommand;
import org.codecranachan.asteroidpush.legacy.ImpactListener;
import org.codecranachan.asteroidpush.legacy.Player;
import org.codecranachan.asteroidpush.legacy.SimulatorCommand;
import org.codecranachan.asteroidpush.legacy.ui.Renderer;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

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

   public void destroy() {
      body.getWorld().destroyBody(body);
   }

   public void render(Renderer renderer) {
      renderer.drawOutline(GeometryConverter.extractOutline(body));
   }

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

   public void handleImpact(Vec2 worldCoordinates, float magnitude) {
      impactDetected = true;
   }
}
