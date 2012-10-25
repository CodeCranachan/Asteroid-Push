package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.Simulator;
import org.skullforge.asteroidpush.appearances.Appearance;

public class TrackingCamera implements Widget, Renderer {

   public TrackingCamera(Simulator simulator) {
      this.simulator = simulator;
      this.tracker = new FixedPositionTracker();
   }

   public void setPositionTracker(PositionTracker tracker) {
      this.tracker = tracker;
   }

   @Override
   public void draw(Appearance appearance) {
      for (Shape s : appearance.getSilhouette()) {
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

      g.translate(frame.getWidth() / 2.0f - frame.getX(), frame.getHeight()
            / 2.0f - frame.getY());
      g.translate(-tracker.getCenter().x, -tracker.getCenter().y);

      float canvasSize = Math.min(frame.getWidth(), frame.getHeight());
      float scale = canvasSize / tracker.getRadius();
      g.scale(scale, scale);

      simulator.render(this);
      g.popTransform();
   }

   private PositionTracker tracker;
   private Simulator simulator;
   private Graphics graphics;
}
