package org.codecranachan.asteroidpush.workshop.tokenboard;

import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;

public class Placement {
   private int orientation;
   private OrthogonalCoordinate pivotCoordinate;

   public Placement(int orientation, OrthogonalCoordinate pivotCoordinate) {
      this.orientation = orientation;
      this.pivotCoordinate = pivotCoordinate;
   }

   public int getOrientation() {
      return orientation;
   }

   public OrthogonalCoordinate getPivotCoordinate() {
      return pivotCoordinate;
   }
}