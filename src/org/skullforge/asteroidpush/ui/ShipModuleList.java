package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.designer.catalogue.ModuleCatalogue;
import org.skullforge.asteroidpush.designer.data.ModuleData;

public class ShipModuleList extends BasicWidget {
   private final float marginX = 3.0f;
   private final float marginY = 3.0f;

   Font font;
   ModuleCatalogue moduleCatalogue;
   int selectedIndex;

   public ShipModuleList(ModuleCatalogue moduleCatalogue, Font font) {
      this.moduleCatalogue = moduleCatalogue;
      this.font = font;
      this.selectedIndex = 0;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      g.setFont(font);
      float top = frame.getY() + marginY;
      int currentIndex = 0;
      for (ModuleData module : this.moduleCatalogue.getModuleData()) {
         if (currentIndex==selectedIndex) {
            g.setColor(Color.gray);
         } else {
            g.setColor(Color.black);
         }
         g.fillRect(frame.getX(), top, frame.getWidth(), font.getLineHeight());
         g.setColor(Color.white);
         g.drawString(module.getName(), frame.getX() + marginX, top);
         top += g.getFont().getLineHeight();
         currentIndex += 1;
      }
      g.setFont(currentFont);

      g.setColor(Color.gray);
      g.drawRoundRect(frame.getX(),
                      frame.getY(),
                      frame.getWidth(),
                      frame.getHeight(),
                      5);

   }

   @Override
   public void mousePressed(int button, int x, int y) {
      selectedIndex = (int) ((y - getFrame().getY()) / font.getLineHeight());
   }
}
