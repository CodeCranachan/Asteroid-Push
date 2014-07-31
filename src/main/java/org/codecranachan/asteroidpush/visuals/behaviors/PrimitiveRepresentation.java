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

   public PrimitiveRepresentation(Primitive primitive, Arrow offset) {
      this.shape = GeometryConverter.convertToSlickShape(primitive, offset);
   }

   public void render(Graphics g) {
      g.setColor(Color.lightGray);
      g.draw(shape);
      g.setColor(Color.darkGray);
      g.fill(shape);
   }

   public int getPriority() {
      return 0;
   }

}
