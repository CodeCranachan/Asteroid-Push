package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.designer.ModuleToken;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class SelectionView extends BasicWidget {

   private BlueprintManipulator manipulator;

   public SelectionView(BlueprintManipulator manipulator) {
      this.manipulator = manipulator;
   }

   @Override
   public void render(Graphics g) {
      TokenView tokenWidget = new TokenView();
      ModuleToken token = new ModuleToken(manipulator.getSelection());
      token.setPlacement(new Placement(0, 0, manipulator.getRotation()
            .getFacing()));
      tokenWidget.setToken(token);

      Rectangle frame = getCenteredSquare(getFrame());

      tokenWidget.resize(frame);
      tokenWidget.render(g);
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
}
