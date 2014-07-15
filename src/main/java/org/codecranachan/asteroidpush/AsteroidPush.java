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

import org.codecranachan.asteroidpush.legacy.Player;
import org.codecranachan.asteroidpush.legacy.ResourceLoader;
import org.codecranachan.asteroidpush.legacy.Scenario;
import org.codecranachan.asteroidpush.state.GameStateFactory;
import org.codecranachan.asteroidpush.state.StateId;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class AsteroidPush extends StateBasedGame {

   public AsteroidPush(GameStateFactory stateFactory,
         ResourceLoader resourceLoader) {
      super("Asteroid Push");
      this.stateFactory = stateFactory;
      this.resourceLoader = resourceLoader;
      this.gameScenario = new Scenario(new Player());
   }

   @Override
   public void initStatesList(GameContainer container) throws SlickException {
      resourceLoader.setGameContainer(container);
      addState(stateFactory.createGameState(StateId.SIMULATION,
                                            resourceLoader,
                                            gameScenario));
      addState(stateFactory.createGameState(StateId.WORKSHOP,
                                            resourceLoader,
                                            gameScenario));
   }

   private GameStateFactory stateFactory;
   private ResourceLoader resourceLoader;
   private Scenario gameScenario;
}
