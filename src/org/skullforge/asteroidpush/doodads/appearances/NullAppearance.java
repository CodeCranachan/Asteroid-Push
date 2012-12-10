package org.skullforge.asteroidpush.doodads.appearances;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;

/**
 * This is a Null-Object for the Appearance interface.
 * 
 * @author Konfuzzyus
 * 
 */

public class NullAppearance implements Appearance {

   @Override
   public ArrayList<Shape> getOutline() {
      return new ArrayList<Shape>();
   }

}
