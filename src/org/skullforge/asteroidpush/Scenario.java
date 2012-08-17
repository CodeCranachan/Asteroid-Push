package org.skullforge.asteroidpush;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.doodads.AsteroidFactory;
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
      list.add(buildAsteroid(new Vec2(5.0f, 4.0f)));
      list.add(buildAsteroid(new Vec2(-2.0f, -2.0f)));
      list.add(buildAsteroid(new Vec2(-4.4f, 2.5f)));
      list.add(buildAsteroid(new Vec2(2.7f, -4.2f)));
      return list;
   }
   
   private Doodad buildPlayfieldBorder() {
      PlayingFieldBorderFactory factory = new PlayingFieldBorderFactory();
      factory.setParameters(100.0f, 50.0f);
      return factory.createDoodad();
   }
   
   private Doodad buildAsteroid(Vec2 position) {
      AsteroidFactory factory = new AsteroidFactory();
      factory.setParameters(position);
      return factory.createDoodad();
   }
}
