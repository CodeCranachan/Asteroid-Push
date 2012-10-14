package org.skullforge.asteroidpush;

import org.newdawn.slick.state.GameState;
import org.skullforge.asteroidpush.designer.ShipDesign;
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
                                    ResourceLoader resourceLoader) {
      GameState state;

      if (null == stateId) {
         stateId = StateInfo.INVALID;
      }

      switch (stateId) {
      case MATCH:
         state = createMatchGameState(resourceLoader);
         break;
      case DESIGNER:
         state = createDesignerGameState(resourceLoader);
         break;
      case INVALID:
      default:
         state = null;
         break;
      }
      return state;
   }

   private MatchGameState createMatchGameState(ResourceLoader resourceLoader) {
      Simulator sim = new Simulator();
      MatchUiFactory uiFactory = new MatchUiFactory(resourceLoader);

      MatchGameState state = new MatchGameState(sim, uiFactory);
      state.setScenario(new Scenario());
      return state;
   }

   private GameState createDesignerGameState(ResourceLoader resourceLoader) {
      ShipDesign design = new ShipDesign();
      DesignerUiFactory uiFactory = new DesignerUiFactory(resourceLoader);

      DesignerGameState state = new DesignerGameState(design, uiFactory);
      return state;
   }
}
