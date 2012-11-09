package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.skullforge.asteroidpush.Simulator;
import org.skullforge.asteroidpush.appearances.Appearance;

public class StaticCamera implements Widget, Renderer {

   public StaticCamera(Simulator sim) {
      this.sim = sim;
   }
   
   @Override
   public void draw(Appearance appearance) {
      for (Shape s : appearance.getOutline()) {
         g.setColor(Color.darkGray);
         g.fill(s);
         g.setColor(Color.gray);
         g.draw(s);
      }
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
      this.g = g;
      g.pushTransform();
      g.translate(frame.getWidth()/2.0f - frame.getX(), frame.getHeight()/2.0f - frame.getY());
      g.scale(5.0f, 5.0f);
      sim.render(this);
      g.popTransform();
   }

   Simulator sim;
   Graphics g;
}
