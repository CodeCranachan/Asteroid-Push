package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.designer.Module;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.grid.Coordinate;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class ShipDesignView implements Widget {

   public ShipDesignView(ShipDesign shipDesign, Font font) {
      this.shipDesign = shipDesign;
      this.font = font;
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
      Collection<Module> modules = shipDesign.getModules();

      for (Module m : modules) {
         Placement currentPlacement = m.getPlacement();
         Rectangle gridFrame = GetFrameForCoordinate(currentPlacement.getCoordinate(), frame);

         Label moduleLabel = new Label(new StringBuffer(m.getName()), font);
         moduleLabel.render(g, gridFrame);
      }
   }

   private Rectangle GetFrameForCoordinate(Coordinate coordinate,
                                           Rectangle parentFrame) {
      float totalSideLength = Math.min(parentFrame.getWidth(),
                                       parentFrame.getHeight());

      float frameW = totalSideLength / gridSize;
      float frameH = totalSideLength / gridSize;
      float frameX = (parentFrame.getWidth() - totalSideLength) / 2.0f
            + coordinate.getX() * (totalSideLength / 5.0f);
      float frameY = (parentFrame.getHeight() - totalSideLength) / 2.0f
            + coordinate.getY() * (totalSideLength / 5.0f);

      return new Rectangle(frameX, frameY, frameW, frameH);
   }

   final private int gridSize = 5;
   private ShipDesign shipDesign;
   private Font font;
}
