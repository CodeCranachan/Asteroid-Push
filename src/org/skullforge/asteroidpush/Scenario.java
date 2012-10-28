package org.skullforge.asteroidpush;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.doodads.AsteroidFactory;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.doodads.PlayingFieldBorderFactory;
import org.skullforge.asteroidpush.doodads.SpaceshipFactory;

/**
 * Encapsulates the information for starting a new match.
 * 
 * @author Konfuzzyus
 * 
 */
public class Scenario {

   public Scenario(Player localPlayer) {
      this.localPlayer = new Player();
   }

   public Player getLocalPlayer() {
      return localPlayer;
   }

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
      
      Doodad ship = buildSpaceship(new Vec2(0.0f, 0.0f));
      localPlayer.setShip(ship);
      list.add(ship);
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
   
   private Doodad buildSpaceship(Vec2 position) {
      SpaceshipFactory factory = new SpaceshipFactory();
      factory.setParameters(position, localPlayer.getShipDesign());
      return factory.createDoodad();
   }

   private Player localPlayer;
}
