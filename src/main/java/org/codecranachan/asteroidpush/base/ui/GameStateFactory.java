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

package org.codecranachan.asteroidpush.base.ui;

import java.util.Collection;
import java.util.Vector;

import org.codecranachan.asteroidpush.base.ResourceLoader;
import org.codecranachan.asteroidpush.base.ui.states.SimulationState;
import org.codecranachan.asteroidpush.base.ui.states.WorkshopState;
import org.newdawn.slick.state.GameState;

/**
 * Abstracts game state creation for the game container.
 * 
 * @author Konfuzzyus
 * 
 */
public class GameStateFactory {
   private ResourceLoader resourceLoader;

   public GameStateFactory(ResourceLoader resourceLoader) {
      this.resourceLoader = resourceLoader;
   }

   public Collection<GameState> createGameStates() {
      Vector<GameState> states = new Vector<GameState>();
      // Note: First added state is the initial state of the game
      states.add(new WorkshopState(resourceLoader));
      states.add(new SimulationState(resourceLoader));
      return states;
   }

}
