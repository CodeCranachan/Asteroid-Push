package org.skullforge.asteroidpush.designer;

public abstract class BaseModule implements Module {
   public BaseModule() {
      position = new GridCoordinate();
   }

   @Override
   public GridCoordinate getPosition() {
      return position;
   }

   @Override
   public void setPosition(GridCoordinate position) {
      this.position.set(position);
   }

   private GridCoordinate position;
}
