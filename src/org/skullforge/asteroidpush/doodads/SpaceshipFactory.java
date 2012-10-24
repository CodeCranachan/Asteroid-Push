package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.parts.Part;
import org.skullforge.asteroidpush.parts.SpaceshipHull;

public class SpaceshipFactory implements DoodadFactory {

   public SpaceshipFactory() {
      spawnPosition = new Vec2();
      design = null;
   }

   public void setParameters(Vec2 spawnPosition, ShipDesign design) {
      this.spawnPosition.set(spawnPosition);
      this.design = design;
   }

   @Override
   public Doodad createDoodad() {
      return initDoodad(new Doodad(doodadName));
   }

   public Doodad initDoodad(Doodad doodad) {
      Part hull = new SpaceshipHull(spawnPosition, design);
      doodad.addPart(hull);
      doodad.addAppearance(new SimpleAppearance(hull));
      return doodad;
   }

   private Vec2 spawnPosition;
   private ShipDesign design;
   final private String doodadName = "Spaceship";
}
