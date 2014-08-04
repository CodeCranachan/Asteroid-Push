package org.codecranachan.asteroidpush.visuals.actors;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ArrowRepresentation implements Representation {
   public Arrow arrow;
   public Color color;
   
   public ArrowRepresentation(Arrow arrow) {
      this.arrow = arrow;
      this.color = Color.white;
   }

   public void render(Graphics g) {
      g.setColor(color);
      
      g.setLineWidth(1.0f);
      
      
      // TODO Auto-generated method stub
      
   }

   public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
   }
}
