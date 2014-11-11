package org.codecranachan.asteroidpush.base.workshop;

import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class ManipulatedArea {
   private OrthogonalCoordinate bottomLeft;
   private OrthogonalCoordinate topRight;

   public ManipulatedArea(Blueprint blueprint) {
      bottomLeft = blueprint.getBottomLeftCorner();
      topRight = blueprint.getTopRightCorner();
   }

   public int getWidth() {
      return topRight.getX() - bottomLeft.getX() + 1;
   }

   public int getHeight() {
      return topRight.getY() - bottomLeft.getY() + 1;
   }
   
   public int getLargestSide() {
      return Math.max(getHeight(), getWidth());
   }

   public OrthogonalCoordinate getBottomLeftCorner() {
      return new OrthogonalCoordinate(bottomLeft);
   }
}
