package org.skullforge.asteroidpush.doodads.appearances;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;

public interface Appearance {
   /**
    * Get this appearance's silhouette, e.g. the outline of its shape. This can
    * be one or more Polygons. The coordinates are given in absolute world
    * coordinates. No extra world coordinate transformation has to be done.
    * 
    * @return an array of graphic polygons defining the appearance.
    */
   ArrayList<Shape> getOutline();
}
