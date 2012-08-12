package org.skullforge.asteroidpush;

import java.util.ArrayList;

import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.doodads.PlayingFieldBorderFactory;

/**
 * Encapsulates the information for starting a new match.
 * 
 * @author Konfuzzyus
 * 
 */
public class Scenario {

   /**
    * Builds an array of Doodads in the state at the beginning of a Match.
    * 
    * @return a list of Doodads that define the initial state of a Match.
    */
   public ArrayList<Doodad> buildDoodads() {
      ArrayList<Doodad> list = new ArrayList<Doodad>();
      list.add(buildPlayfieldBorder());
      return list;
   }
   
   private Doodad buildPlayfieldBorder() {
      PlayingFieldBorderFactory factory = new PlayingFieldBorderFactory();
      factory.setParameters(100.0f, 50.0f);
      return factory.createDoodad();
   }
}
