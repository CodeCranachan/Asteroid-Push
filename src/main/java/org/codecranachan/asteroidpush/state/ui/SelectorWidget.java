package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.newdawn.slick.Graphics;

public class SelectorWidget extends BasicWidget {
   private WorkshopCoordinator coordinator;

   public SelectorWidget(WorkshopCoordinator coordinator, ResourceLoader loader) {
      this.coordinator = coordinator;
   }
   
   @Override
   public void render(Graphics g) {
      if (getFrame() == null) {
         return;
      }
      
      // TODO
   }
   
   @Override
   public void mousePressed(int button, int x, int y) {
      if (getFrame() == null) {
         return;
      }
      // TODO
   }
}
