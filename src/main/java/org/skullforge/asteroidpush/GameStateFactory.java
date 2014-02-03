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

import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.ui.DesignerUiFactory;
import org.skullforge.asteroidpush.ui.MatchUiFactory;

/**
 * Abstracts game state creation for the game container.
 * 
 * @author Konfuzzyus
 * 
 */
public class GameStateFactory {

   /**
    * Creates a game state for the given state id.
    * 
    * @param stateId
    *           id of the game state
    * @return a freshly assembled game state to be used for that state id.
    */
   public GameState createGameState(StateInfo stateId,
                                    ResourceLoader resourceLoader,
                                    Scenario scenario) {
      GameState state;

      if (null == stateId) {
         stateId = StateInfo.INVALID;
      }

      switch (stateId) {
      case MATCH:
         state = createMatchGameState(resourceLoader, scenario);
         break;
      case DESIGNER:
         state = createDesignerGameState(resourceLoader, scenario);
         break;
      case INVALID:
      default:
         state = null;
         break;
      }
      return state;
   }

   private MatchGameState createMatchGameState(ResourceLoader resourceLoader, Scenario scenario) {
      Simulator sim = new Simulator();
      MatchUiFactory uiFactory = new MatchUiFactory(resourceLoader);

      MatchGameState state = new MatchGameState(sim, uiFactory);
      state.setScenario(scenario);
      return state;
   }

   private GameState createDesignerGameState(ResourceLoader resourceLoader, Scenario scenario) {
      DesignerUiFactory uiFactory = new DesignerUiFactory(resourceLoader);

      DesignerGameState state = new DesignerGameState(uiFactory);
      state.setScenario(scenario);
      return state;
   }
}
