package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class DesignerLayout implements Widget {

   Widget catalogue;
   Widget blueprint;

   public DesignerLayout() {
      this.catalogue = null;
      this.blueprint = null;
   }

   public void setCatalogueWidget(Widget widget) {
      this.catalogue = widget;
   }

   public void setBlueprintWidget(Widget widget) {
      this.blueprint = widget;
   }

   @Override
   public void render(Graphics g, Rectangle frame) {
      float ratio = 0.75f;

      Rectangle blueprintFrame = new Rectangle(frame.getX(), frame.getY(),
            frame.getWidth() * ratio, frame.getHeight());
      if (blueprint != null) {

         blueprint.render(g, blueprintFrame);
      }
      Rectangle catalogueFrame = new Rectangle(frame.getX()
            + blueprintFrame.getWidth(), frame.getY(), frame.getWidth()
            - blueprintFrame.getWidth(), frame.getHeight());
      if (catalogue != null) {
         catalogue.render(g, catalogueFrame);
      }
   }

}
