package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
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
      return initDoodad(new Doodad(doodadName));
   }

   public void setParameters(float fieldWidth, float fieldHeight) {
      this.fieldWidth = fieldWidth;
      this.fieldHeight = fieldHeight;
   }

   public Doodad initDoodad(Doodad doodad) {
      Vec2 innerDiagonal = new Vec2(fieldWidth / 2.0f, fieldHeight / 2.0f);
      Vec2 border = new Vec2(borderThickness, borderThickness);
      Vec2 outerDiagonal = innerDiagonal.add(border);
      doodad.addPart(new StaticBox(innerDiagonal, outerDiagonal));
      return doodad;
   }

   private float fieldWidth;
   private float fieldHeight;
   final private float borderThickness = 10.0f;
   final private String doodadName = "PlayingFieldBorder";
}
