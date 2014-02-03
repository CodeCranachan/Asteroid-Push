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

package org.skullforge.asteroidpush;

import java.util.Collection;
import java.util.LinkedList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.entities.AsteroidFactory;
import org.skullforge.asteroidpush.entities.PlayingFieldBorderFactory;
import org.skullforge.asteroidpush.entities.spaceship.SpaceshipFactory;

public class Scenario {

   private Player localPlayer;

   public Scenario(Player localPlayer) {
      this.localPlayer = new Player();
   }

   public Player getLocalPlayer() {
      return localPlayer;
   }

   public Collection<SimulatorCommand> getSetupCommands(World world) {
      LinkedList<SimulatorCommand> commands = new LinkedList<SimulatorCommand>();

      PlayingFieldBorderFactory borderFactory = new PlayingFieldBorderFactory(
            world);
      commands.push(new SpawnEntityCommand(borderFactory, new Vec2(), null));

      AsteroidFactory asteroidFactory = new AsteroidFactory(world);
      commands.push(new SpawnEntityCommand(asteroidFactory, new Vec2(10.0f,
            4.0f), null));
      commands.push(new SpawnEntityCommand(asteroidFactory, new Vec2(-4.0f,
            -2.0f), null));
      commands.push(new SpawnEntityCommand(asteroidFactory, new Vec2(-9.4f,
            2.5f), null));
      commands.push(new SpawnEntityCommand(asteroidFactory, new Vec2(6.0f,
            -4.2f), null));

      SpaceshipFactory shipFactory = new SpaceshipFactory(
            localPlayer.getShipDesign(), world);
      commands.push(new SpawnEntityCommand(shipFactory, new Vec2(0.0f, 0.0f),
            localPlayer));

      return commands;
   }
}
