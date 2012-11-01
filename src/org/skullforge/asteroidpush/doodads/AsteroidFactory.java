package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.parts.Debris;
import org.skullforge.asteroidpush.parts.Chassis;

public class AsteroidFactory implements DoodadFactory {

   public AsteroidFactory() {
      spawnPosition = new Vec2();
   }
   
   public void setParameters(Vec2 spawnPosition) {
      this.spawnPosition.set(spawnPosition);
   }
   
   @Override
   public Doodad createDoodad() {
      return initDoodad(new Doodad());
   }
   
   public Doodad initDoodad(Doodad doodad) {
      Chassis debris = new Debris(spawnPosition);
      doodad.setChassis(debris);
      doodad.setAppearance(new SimpleAppearance(debris));
      return doodad;
   }
   
   Vec2 spawnPosition;
}
