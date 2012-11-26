package org.skullforge.asteroidpush.designer.modules.data;

import org.skullforge.asteroidpush.designer.grid.Rotation;

public class GlueData {
   public boolean doStickToFront() {
      return true;
   }

   public boolean doStickToRight() {
      return true;
   }

   public boolean doStickToBack() {
      return true;
   }

   public boolean doStickToLeft() {
      return true;
   }
   
   public GlueData getRotated(Rotation rotation) {
      return new GlueData();
   }
}
