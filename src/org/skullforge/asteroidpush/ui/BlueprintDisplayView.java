package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.jbox2d.common.MathUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class BlueprintDisplayView extends BasicWidget {
   final private Blueprint shipDesign;
   final private BlueprintManipulator manipulator;

   public BlueprintDisplayView(Blueprint shipDesign,
         BlueprintManipulator manipulator) {
      this.shipDesign = shipDesign;
      this.manipulator = manipulator;
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
      GridVector relativePosition = coordinate.sub(getGridOffset());

      float gridX = -relativePosition.getY() * tileLength;
      float gridY = -relativePosition.getX() * tileLength;

      return new Rectangle(square.getX() + gridX, square.getY() + gridY,
            tileLength, tileLength);
   }

   private int getGridSize() {
      int gridSize = Math.max(shipDesign.getWidth(), shipDesign.getHeight());
      gridSize += 2;
      return gridSize;
   }

   private GridVector getGridOffset() {
      int gridSize = getGridSize();
      GridVector delta = new GridVector((gridSize - shipDesign.getWidth()) / 2,
            (gridSize - shipDesign.getHeight()) / 2);
      GridVector max = shipDesign.getMax();
      return max.add(delta);
   }

   public GridVector getCoordinateForPoint(Vector2f point, Rectangle parentFrame) {
      Rectangle square = getCenteredSquare(parentFrame);
      float tileLength = square.getHeight() / getGridSize();

      if (!square.contains(point.x, point.y)) {
         return null;
      }

      GridVector delta = new GridVector(-MathUtils.floor((point.y - square
            .getY()) / tileLength), -MathUtils.floor((point.x - square.getX())
            / tileLength));

      return delta.add(getGridOffset());
   }

   @Override
   public void render(Graphics g) {
      if (getFrame() == null) {
         return;
      }

      Collection<ModuleToken> modules = shipDesign.getTokens();

      Rectangle printSquare = getCenteredSquare(getFrame());
      g.setAntiAlias(true);
      g.setColor(Color.blue);
      g.fill(printSquare);
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
      g.setLineWidth(3.0f);
      g.draw(printSquare);

      Rectangle reference = getFrameForCoordinate(new GridVector(), getFrame());
      float lit = reference.getWidth();
      g.setLineWidth(1.5f);
      g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
      for (int i = 1; i < getGridSize(); ++i) {
         float x = printSquare.getX() + lit * i;
         g.drawLine(x, printSquare.getMinY(), x, printSquare.getMaxY());
         float y = printSquare.getY() + lit * i;
         g.drawLine(printSquare.getMinX(), y, printSquare.getMaxX(), y);
      }

      TokenView tokenWidget = new TokenView();
      for (ModuleToken m : modules) {
         tokenWidget.setToken(m);

         Placement currentPlacement = m.getPlacement();
         Rectangle gridFrame = getFrameForCoordinate(currentPlacement.getCoordinate(),
                                                     getFrame());
         tokenWidget.resize(gridFrame);
         tokenWidget.render(g);
      }

      Vector2f hover = getHover();
      if (hover != null) {
         GridVector hoveredCoordinate = getCoordinateForPoint(hover, getFrame());
         if (hoveredCoordinate != null) {
            Rectangle highlightFrame = getFrameForCoordinate(hoveredCoordinate,
                                                             getFrame());
            g.setLineWidth(3.5f);
            g.setColor(new Color(1.0f, 1.0f, 0.0f, 0.75f));
            g.draw(highlightFrame);
         }
      }
   }

   @Override
   public void mousePressed(int button, int x, int y) {
      GridVector coordinate = getCoordinateForPoint(new Vector2f(x, y), getFrame());
      
      switch (button) {
      case Input.MOUSE_LEFT_BUTTON:
         manipulator.placeModule(coordinate);
         break;
      case Input.MOUSE_RIGHT_BUTTON:
         manipulator.removeModule(coordinate);
         break;
      }
   }
}
