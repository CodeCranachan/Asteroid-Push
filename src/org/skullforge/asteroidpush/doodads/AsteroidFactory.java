package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.assemblies.Assembly;
import org.skullforge.asteroidpush.assemblies.Debris;

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
      Assembly debris = new Debris(spawnPosition);
      doodad.setAssembly(debris);
      doodad.setAppearance(new SimpleAppearance(debris));
      return doodad;
   }
   
   Vec2 spawnPosition;
}
