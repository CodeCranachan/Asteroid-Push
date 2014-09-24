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

import java.util.Stack;

import org.codecranachan.asteroidpush.state.GameStateFactory;
import org.codecranachan.asteroidpush.state.StateContext;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class AsteroidPush extends StateBasedGame {
   private ResourceLoader resourceLoader;
   private GameInstance mainGame;
   private Settings settings;
   private Stack<StateContext> contextStack;

   public AsteroidPush(ResourceLoader resourceLoader) {
      super("Asteroid Push");
      contextStack = new Stack<StateContext>();
      this.resourceLoader = resourceLoader;
      // TODO Load settings from file
      this.settings = new Settings();
   }

   @Override
   public void initStatesList(GameContainer container) throws SlickException {
      resourceLoader.setGameContainer(container);
      GameStateFactory factory = new GameStateFactory(resourceLoader);
      for (GameState state : factory.createGameStates()) {
         addState(state);
      }
   }

   public GameInstance getGame() {
      return mainGame;
   }

   public Settings getSettings() {
      return settings;
   }

   public void pushContext(StateContext context) {
      contextStack.push(context);
      context.enterContext(this);
   }

   public void popContext() {
      if (!contextStack.empty()) {
         contextStack.pop().exitContext(this);
      }
   }
}
