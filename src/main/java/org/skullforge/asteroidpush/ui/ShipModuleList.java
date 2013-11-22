package org.skullforge.asteroidpush.ui;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.designer.BlueprintManipulator;
import org.skullforge.asteroidpush.designer.catalogue.ModuleCatalogue;
import org.skullforge.asteroidpush.designer.data.ModuleData;

public class ShipModuleList extends BasicWidget {
   private final float marginX = 3.0f;
   private final float marginY = 3.0f;

   Font font;
   Vector<ModuleData> moduleCatalogue;
   int selectedIndex;
   BlueprintManipulator manipulator;

   public ShipModuleList(BlueprintManipulator manipulator, ModuleCatalogue moduleCatalogue, Font font) {
      this.moduleCatalogue = new Vector<ModuleData>(moduleCatalogue.getModuleData());
      this.font = font;
      this.selectedIndex = 0;
      this.manipulator = manipulator;
   }

   @Override
   public void render(Graphics g) {
      Rectangle frame = getFrame();
      Font currentFont = g.getFont();
      g.setFont(font);
      float top = frame.getY() + marginY;
      for (int index=0; index < this.moduleCatalogue.size(); index++) {
         if (index==selectedIndex) {
            g.setColor(Color.gray);
         } else {
            g.setColor(Color.black);
         }
         g.fillRect(frame.getX(), top, frame.getWidth(), font.getLineHeight());
         g.setColor(Color.white);
         g.drawString(this.moduleCatalogue.get(index).getName(), frame.getX() + marginX, top);
         top += g.getFont().getLineHeight();
      }
      g.setFont(currentFont);

      g.setLineWidth(2.0f);
      g.setColor(Color.gray);
      g.drawRoundRect(frame.getX(),
                      frame.getY(),
                      frame.getWidth(),
                      frame.getHeight(),
                      5);

   }

   @Override
   public void mousePressed(int button, int x, int y) {
      int newIndex = (int) ((y - getFrame().getY()) / font.getLineHeight());
      if (newIndex < this.moduleCatalogue.size()) {
         selectedIndex = newIndex;
         if (manipulator != null) {
            manipulator.select(this.moduleCatalogue.get(selectedIndex));
         }
      }
   }
}
