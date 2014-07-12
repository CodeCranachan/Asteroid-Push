//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.legacy.ui;

import java.util.Collection;

import org.codecranachan.asteroidpush.legacy.Simulator;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class TrackingCamera extends BasicWidget implements Renderer {

   public TrackingCamera(Simulator simulator) {
      this.simulator = simulator;
      this.tracker = new FixedPositionTracker();
   }

   public void setPositionTracker(PositionTracker tracker) {
      this.tracker = tracker;
   }

   public void drawOutline(Collection<Shape> outline) {
      for (Shape s : outline) {
         graphics.setColor(Color.darkGray);
         graphics.fill(s);
         graphics.setColor(Color.gray);
         graphics.draw(s);
      }
   }

   public void drawLine(Point start, Point end, float width, Color color) {
      graphics.setColor(color);
      float oldWidth = graphics.getLineWidth();
      graphics.setLineWidth(width);
      graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
      graphics.setLineWidth(oldWidth);
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      this.graphics = g;
      g.pushTransform();
      g.setAntiAlias(true);
      g.setLineWidth(1.5f);

      Vec2 centeringVector = new Vec2(frame.getWidth() / 2.0f,
            frame.getHeight() / 2.0f);
      Vec2 frameVector = new Vec2(-frame.getX(), -frame.getY());
      Vec2 translationVector = centeringVector.add(frameVector);
      g.translate(translationVector.x, translationVector.y);

      float canvasSize = Math.min(frame.getWidth(), frame.getHeight());
      float scale = canvasSize / tracker.getRadius();
      g.scale(scale, -scale);

      Vec2 trackerVector = tracker.getCenter().negate();
      g.translate(trackerVector.x, trackerVector.y);

      simulator.render(this);
      g.popTransform();
   }

   private PositionTracker tracker;
   private Simulator simulator;
   private Graphics graphics;
}
