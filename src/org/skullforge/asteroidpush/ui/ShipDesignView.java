package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ShipDesignView implements Widget {
   final private Blueprint shipDesign;

   public ShipDesignView(Blueprint shipDesign) {
      this.shipDesign = shipDesign;
   }

   public Rectangle getCenteredSquare(Rectangle parentFrame) {
      float sideLength = Math.min(parentFrame.getWidth(),
                                  parentFrame.getHeight());

      float offsetX = parentFrame.getX();
      offsetX += (parentFrame.getWidth() - sideLength) / 2.0f;
      float offsetY = parentFrame.getY();
      offsetY += (parentFrame.getHeight() - sideLength) / 2.0f;

      Rectangle rect = new Rectangle(offsetX, offsetY, sideLength, sideLength);

      return rect;
   }

   public Rectangle getFrameForCoordinate(GridVector coordinate,
                                          Rectangle parentFrame) {

      Rectangle square = getCenteredSquare(parentFrame);

      float tileLength = square.getHeight() / getGridSize();

      float gridX = (coordinate.getX() - (shipDesign.getMin().getX() - 1))
            * tileLength;
      float gridY = ((shipDesign.getMax().getY() + 1) - coordinate.getY())
            * tileLength;

      return new Rectangle(square.getX() + gridX, square.getY() + gridY,
            tileLength, tileLength);
   }

   private int getGridSize() {
      GridVector min = shipDesign.getMin();
      GridVector max = shipDesign.getMax();

      int gridSize = Math.max(max.getX() - min.getX(), max.getY() - min.getY());
      gridSize += 3;

      return gridSize;
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
      Collection<ModuleToken> modules = shipDesign.getTokens();

      Rectangle printSquare = getCenteredSquare(frame);
      g.setAntiAlias(true);
      g.setColor(Color.blue);
      g.fill(printSquare);
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
      g.setLineWidth(3.0f);
      g.draw(printSquare);

      Rectangle reference = getFrameForCoordinate(new GridVector(), frame);
      float lit = reference.getWidth();
      g.setLineWidth(1.5f);
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
      for (int i = 1; i < getGridSize(); ++i) {
         float x = printSquare.getX() + lit * i;
         g.drawLine(x, printSquare.getMinY(), x, printSquare.getMaxY());
         float y = printSquare.getY() + lit * i;
         g.drawLine(printSquare.getMinX(), y, printSquare.getMaxX(), y);
      }

      for (ModuleToken m : modules) {
         Placement currentPlacement = m.getPlacement();
         Rectangle gridFrame = getFrameForCoordinate(currentPlacement.getCoordinate(),
                                                     frame);

         g.setLineWidth(1.5f);
         g.setColor(Color.white);
         for (Shape shape : m.getData().getOutline()) {
            Transform translation = Transform
                  .createTranslateTransform(gridFrame.getCenterX(),
                                            gridFrame.getCenterY());
            Transform scale = Transform.createScaleTransform(gridFrame
                  .getWidth() * 0.8f, - gridFrame.getHeight() * 0.8f);
            Transform rotation = Transform.createRotateTransform(m.getPlacement().getRotation().getRadians());
            shape = shape.transform(rotation);
            shape = shape.transform(scale);
            shape = shape.transform(translation);
            g.draw(shape);
         }
      }
   }
}
