package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.Simulator;

public class TrackingCamera implements Widget, Renderer {

   public TrackingCamera(Simulator simulator) {
      this.simulator = simulator;
      this.tracker = new FixedPositionTracker();
   }

   public void setPositionTracker(PositionTracker tracker) {
      this.tracker = tracker;
   }

   @Override
   public void drawOutline(Collection<Shape> outline) {
      for (Shape s : outline) {
         graphics.setColor(Color.darkGray);
         graphics.fill(s);
         graphics.setColor(Color.gray);
         graphics.draw(s);
      }
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
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
      g.scale(scale, - scale);

      Vec2 trackerVector = tracker.getCenter().negate();
      g.translate(trackerVector.x, trackerVector.y);

      simulator.render(this);
      g.popTransform();
   }

   private PositionTracker tracker;
   private Simulator simulator;
   private Graphics graphics;
}
