package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public interface Renderer {   
   void drawOutline(Collection<Shape> outline);
   void drawLine(Point start, Point end, float width, Color color);
}
