package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.appearances.SimpleAppearance;
import org.skullforge.asteroidpush.parts.Chassis;
import org.skullforge.asteroidpush.parts.StaticBox;

/**
 * Builds a Doodad that represents the playing field border.
 * 
 * @author Konfuzzyus
 */
public class PlayingFieldBorderFactory implements DoodadFactory {

   public PlayingFieldBorderFactory() {
      fieldWidth = 256.0f;
      fieldHeight = 256.0f;
   }

   @Override
   public Doodad createDoodad() {
      return initDoodad(new Doodad());
   }

   public void setParameters(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }

   public Doodad initDoodad(Doodad doodad) {
      Vec2 innerDiagonal = new Vec2(fieldWidth / 2.0f, fieldHeight / 2.0f);
      Vec2 border = new Vec2(borderThickness, borderThickness);
      Vec2 outerDiagonal = innerDiagonal.add(border);
      Chassis box = new StaticBox(innerDiagonal, outerDiagonal);
      doodad.setChassis(box);
      doodad.setAppearance(new SimpleAppearance(box));
      return doodad;
   }

   private float fieldWidth;
   private float fieldHeight;
   final private float borderThickness = 10.0f;
}
