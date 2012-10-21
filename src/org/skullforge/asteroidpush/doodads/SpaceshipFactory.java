package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.designer.GridCoordinate;
import org.skullforge.asteroidpush.designer.Module;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.parts.Block;
import org.skullforge.asteroidpush.parts.Material;
import org.skullforge.asteroidpush.parts.Part;

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
      for (Module module : design.getModules()) {
         GridCoordinate coordinate = module.getPosition();
         Vec2 modulePosition = new Vec2(coordinate.getX() * 0.5f, coordinate.getY() * 0.5f);
         modulePosition.addLocal(spawnPosition);
         Part block = new Block(modulePosition, Material.METAL);
         doodad.addPart(block);
         doodad.addAppearance(new SimpleAppearance(block));
      }
      return doodad;
   }
   
   private Vec2 spawnPosition;
   private ShipDesign design;
   final private String doodadName = "Spaceship";
}
