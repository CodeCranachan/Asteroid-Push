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

package org.codecranachan.asteroidpush;

import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.EntityFactory;
import org.jbox2d.common.Vec2;

public class SpawnEntityCommand implements SimulatorCommand {

   private EntityFactory factory;
   private Vec2 location;
   private Player owner;

   public SpawnEntityCommand(EntityFactory factory, Vec2 location, Player owner) {
      this.factory = factory;
      this.location = location;
      this.owner = owner;
   }

   public void execute(Simulator simulator) {
      Entity entity = factory.createEntity(location);
      entity.setOwner(owner);
      if (owner != null) {
         owner.setShip(entity);
      }
      simulator.addEntity(entity);
   }

}