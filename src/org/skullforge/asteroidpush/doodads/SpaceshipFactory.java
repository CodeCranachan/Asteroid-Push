package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.assemblies.SpaceshipHull;
import org.skullforge.asteroidpush.designer.Blueprint;

public class SpaceshipFactory implements DoodadFactory {

   public SpaceshipFactory() {
      spawnPosition = new Vec2();
      design = null;
   }

   public void setParameters(Vec2 spawnPosition, Blueprint design) {
      this.spawnPosition.set(spawnPosition);
      this.design = design;
   }

   @Override
   public Doodad createDoodad() {
      return initDoodad(new Doodad());
   }

   public Doodad initDoodad(Doodad doodad) {
      SpaceshipHull hull = new SpaceshipHull(spawnPosition, design);
      doodad.setAssembly(hull);
      doodad.setLogic(hull);
      doodad.setAppearance(new SimpleAppearance(hull));
      return doodad;
   }

   private Vec2 spawnPosition;
   private Blueprint design;
}
