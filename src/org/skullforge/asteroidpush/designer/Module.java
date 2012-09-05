package org.skullforge.asteroidpush.designer;

public interface Module {
   String getName();

   GridCoordinate getPosition();

   void setPosition(GridCoordinate position);
}