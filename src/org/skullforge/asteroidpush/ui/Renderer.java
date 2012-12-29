package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Shape;

public interface Renderer {   
   void drawOutline(Collection<Shape> outline);
   void drawLine(Vec2 start, Vec2 end);
}
