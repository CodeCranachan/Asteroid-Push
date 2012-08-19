package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.parts.Debris;
import org.skullforge.asteroidpush.parts.Part;

public class AsteroidFactory implements DoodadFactory {

   public AsteroidFactory() {
      spawnPosition = new Vec2();
   }
   
   public void setParameters(Vec2 spawnPosition) {
      this.spawnPosition.set(spawnPosition);
   }
   
   @Override
   public Doodad createDoodad() {
      return initDoodad(new Doodad(doodadName));
   }
   
   public Doodad initDoodad(Doodad doodad) {
      Part debris = new Debris(spawnPosition);
      doodad.addPart(debris);
      doodad.addAppearance(new SimpleAppearance(debris));
      return doodad;
   }
   
   Vec2 spawnPosition;
   final private String doodadName = "Asteroid";
}
