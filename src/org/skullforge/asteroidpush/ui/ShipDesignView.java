package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ShipDesignView implements Widget {
   final private Font font;
   final private Blueprint shipDesign;

   public ShipDesignView(Blueprint shipDesign, Font font) {
      this.shipDesign = shipDesign;
      this.font = font;
   }

   private Rectangle GetFrameForCoordinate(GridVector coordinate,
                                           Rectangle parentFrame) {
      float totalSideLength = Math.min(parentFrame.getWidth(),
                                       parentFrame.getHeight());

      float frameW = totalSideLength / getGridSize();
      float frameH = totalSideLength / getGridSize();

      float gridX = coordinate.getX() * (totalSideLength / getGridSize());
      float gridY = coordinate.getY() * (totalSideLength / getGridSize());

      float frameX = (parentFrame.getWidth() - totalSideLength) / 2.0f;
      float frameY = (parentFrame.getHeight() - totalSideLength) / 2.0f;

      return new Rectangle(frameX + gridX, frameY + gridY, frameW, frameH);
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

      for (ModuleToken m : modules) {
         Placement currentPlacement = m.getPlacement();
         Rectangle gridFrame = GetFrameForCoordinate(currentPlacement.getCoordinate(),
                                                     frame);

         Label moduleLabel = new Label(new StringBuffer(m.getData().getName()),
               font);
         moduleLabel.render(g, gridFrame);
      }
   }
}
