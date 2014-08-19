package org.codecranachan.asteroidpush.visuals.behaviors;

import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public class PrimitiveRepresentation implements Representation {

   private Shape shape;

   public PrimitiveRepresentation(Primitive primitive) {
      this.shape = GeometryConverter
            .convertToSlickShape(primitive, new Arrow());
   }

   public void render(Graphics g) {
      Color fillColor = new Color(0.5f, 0.5f, 0.5f, 0.5f);
      g.setColor(fillColor);
      g.fill(shape);
      g.setLineWidth(2.5f);
      g.setColor(Color.lightGray);
      g.draw(shape);
   }

   public int getPriority() {
      return 0;
   }

}
